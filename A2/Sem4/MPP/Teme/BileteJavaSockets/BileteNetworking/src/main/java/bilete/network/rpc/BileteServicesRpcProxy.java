package bilete.network.rpc;

import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.domain.User;
import bilete.network.dto.DTOUtils;
import bilete.network.dto.UserDTO;
import bilete.network.transfer.Request;
import bilete.network.transfer.RequestType;
import bilete.network.transfer.Response;
import bilete.network.transfer.ResponseType;
import bilete.services.BileteException;
import bilete.services.IBileteObserver;
import bilete.services.IBileteServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BileteServicesRpcProxy implements IBileteServices {
    private String host;
    private int port;

    private IBileteObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public BileteServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();
    }

    public void login(User user, IBileteObserver client) throws BileteException {
        initializeConnection();
        UserDTO udto= DTOUtils.getDto(user);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new BileteException("login rpc service proxy "+err);
        }
    }

    public void logout(User user, IBileteObserver client) throws BileteException {
        UserDTO udto= DTOUtils.getDto(user);
        Request req=new Request.Builder().type(RequestType.LOGOUT).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        closeConnection();
        if (response.type() == ResponseType.ERROR){
            String err=response.data().toString();
            throw new BileteException("logout rpc service proxy "+err);
        }
    }

    public Show[] findAllArtisti() throws BileteException {
        Request req=new Request.Builder().type(RequestType.GET_ARTISTI).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type() == ResponseType.ERROR){
            String err=response.data().toString();
            throw new BileteException("find all artisti rpc service proxy "+err);
        }
        Show[] artisti=(Show[])response.data();
        return artisti;
    }

    public Show[] findShowsOnDate(LocalDate date) throws BileteException {
        Request req=new Request.Builder().type(RequestType.GET_SHOWS).data(date).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type() == ResponseType.ERROR){
            String err=response.data().toString();
            throw new BileteException("find shows on date rpc service proxy "+err);
        }
        Show[] shows=(Show[])response.data();
        return shows;
    }

    public void sellTicket(Ticket ticket) throws BileteException {
        Request req=new Request.Builder().type(RequestType.BUY_TICKET).data(ticket).build();
        sendRequest(req);
        // no need to read response, as it's not put in the queue
        // TODO TODO TODO - do i need to catch the okresponse here?
        Response response=readResponse();
        if (response.type() == ResponseType.ERROR){
            String err=response.data().toString();
            throw new BileteException("sell ticket rpc service proxy "+err);
        }
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request)throws BileteException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new BileteException("Error sending object "+e);
        }

    }

    private Response readResponse() throws BileteException {
        Response response=null;
        try{

            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() throws BileteException {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            System.out.println("Error initializing server rpc proxy connection:\n");
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    private boolean isUpdate(Response response) {
        return response.type()== ResponseType.UPDATE;
    }

    private void handleUpdate(Response response) {
        if(response.type() == ResponseType.UPDATE) {
            Ticket ticket=(Ticket)response.data();
            try {
                System.out.println("Bilete Services RPC Proxy - handle update - notify bilete update");
                System.out.println("Client: "+client.getClass().getName());
                client.bileteUpdated(ticket);
                System.out.println("Bilete Services RPC Proxy - handle update - notify bilete update - done");
            } catch (Exception e) {
                System.out.println("Error notifying bilete update in service worker:\n");
                e.printStackTrace();
            }
        }
    }

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{
                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            System.out.println("Error adding response to queue:\n");
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
