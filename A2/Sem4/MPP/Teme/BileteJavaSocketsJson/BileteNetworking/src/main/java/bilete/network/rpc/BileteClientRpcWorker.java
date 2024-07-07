package bilete.network.rpc;

import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.domain.User;
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

public class BileteClientRpcWorker implements Runnable, IBileteObserver {
    private IBileteServices server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    public BileteClientRpcWorker(IBileteServices server, Socket connection)
    {
        this.server = server;
        this.connection = connection;
        System.out.println("BileteClientRpcWorker.BileteClientRpcWorker");
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("BileteClientRpcWorker.run");
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                System.out.println("Client Worker run Error:\n");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Client Worker run Error:\n");
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Client Worker run Error:\n");
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Client Worker run Error: "+e);
        }
    }

    private Response handleRequest(Request request){
        Response response=null;
        System.out.println("BileteClientRpcWorker.handleRequest");
        if (request.type()== RequestType.LOGIN){
            System.out.println("Login request ..." + request.type());
            //UserDTO udto = (UserDTO)request.data();
            //User user = DTOUtils.getFromDto(udto);
            User user = (User)request.data();
            try {
                server.login(user, this);
                return okResponse;
            } catch (BileteException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data("Handle request error: "+e.getMessage()).build();
            }
        }
        if (request.type()==RequestType.LOGOUT){
            System.out.println("Logout request");
            //UserDTO udto = (UserDTO)request.data();
            //User user = DTOUtils.getFromDto(udto);
            User user = (User)request.data();
            try {
                server.logout(user, this);
                connected=false;
                return okResponse;
            } catch (BileteException e) {
                return new Response.Builder().type(ResponseType.ERROR).data("Handle request error: "+e.getMessage()).build();
            }
        }
        if(request.type()==RequestType.GET_ARTISTI){
            System.out.println("Get artisti request");
            try {
                Show[] artisti = server.findAllArtisti();
                return new Response.Builder().type(ResponseType.OK).data(artisti).build();
            } catch (BileteException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if(request.type()==RequestType.GET_SHOWS){
            System.out.println("Get shows request");
            LocalDate date = (LocalDate)request.data();
            try {
                Show[] shows = server.findShowsOnDate(date);
                return new Response.Builder().type(ResponseType.OK).data(shows).build();
            } catch (BileteException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type()==RequestType.BUY_TICKET){
            System.out.println("Buy ticket request");
            Ticket ticket = (Ticket)request.data();
            try {
                server.sellTicket(ticket);
                return okResponse;
            } catch (BileteException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }

    @Override
    public void bileteUpdated(Ticket ticket) throws BileteException {
        Response resp=new Response.Builder().type(ResponseType.UPDATE).data(ticket).build();
        System.out.println("BileteClientRpcWorker.bileteUpdated");
        try {
            sendResponse(resp);
            System.out.println("Sent response");
        } catch (IOException e){
            throw new BileteException("Error sending response:\n "+e);
        }
    }
}
