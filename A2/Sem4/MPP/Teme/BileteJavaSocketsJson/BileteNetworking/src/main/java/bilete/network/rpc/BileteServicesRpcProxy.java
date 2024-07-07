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
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BileteServicesRpcProxy implements IBileteServices {
    private String host;
    private int port;

    private IBileteObserver client;

    private BufferedReader input;
    private OutputStreamWriter output;
    private Socket connection;
    private ObjectMapper objectMapper;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public BileteServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();

        JsonFactory factory = new JsonFactory();
        factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        objectMapper = new ObjectMapper(factory);
    }

    public void login(User user, IBileteObserver client) throws BileteException {
        initializeConnection();
        //UserDTO udto= DTOUtils.getDto(user);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(user).build();
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
        //UserDTO udto= DTOUtils.getDto(user);
        Request req=new Request.Builder().type(RequestType.LOGOUT).data(user).build();
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
        return getShows(response);
    }

    public Show[] findShowsOnDate(LocalDate date) throws BileteException {
        Request req=new Request.Builder().type(RequestType.GET_SHOWS).data(date).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type() == ResponseType.ERROR){
            String err=response.data().toString();
            throw new BileteException("find shows on date rpc service proxy "+err);
        }
        return getShows(response);
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

    private Show[] getShows(Response response) throws BileteException {
        try {
            Show[] artisti = objectMapper.readValue(response.data().toString(), Show[].class);
            return artisti;
        }
        catch (IOException e){
            throw new BileteException("find all artisti rpc service proxy deserialize failed: "+e);
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
//            String json = objectMapper.writeValueAsString(request);
//            output.write(json);
//            output.flush();
            if(request.type()==RequestType.GET_SHOWS){
                LocalDate date = (LocalDate) request.data();
                String dateStr = date.toString();
                request = new Request.Builder().type(RequestType.GET_SHOWS).data(dateStr).build();
            }
            objectMapper.writeValue(output,request);
            output.write("\n");
            output.flush();
            System.out.println("Request sent: "+objectMapper.writeValueAsString(request));
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
            output=new OutputStreamWriter(connection.getOutputStream());
            output.flush();
            input=new BufferedReader(new InputStreamReader(connection.getInputStream()));
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

    private void handleUpdate(Response response) throws IOException {
        if(response.type() == ResponseType.UPDATE) {
            Ticket ticket=objectMapper.readValue(response.data().toString(),Ticket.class);
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

        private void processResponse(String jsonResponse) throws BileteException, IOException {
            Response response=objectMapper.readValue(jsonResponse,Response.class);
            System.out.println("deserialized response received "+response);
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
        }

        public void run() {
            while(!finished){
                try {
                    System.out.println("Reading response from server");
                    String jsonResponse=input.readLine();
                    if(jsonResponse==null) continue;
                    jsonResponse = jsonResponse.substring(1);
                    System.out.println("text response received "+jsonResponse);
                    try{
                        processResponse(jsonResponse);
                    }
                    catch (IOException e)
                    {
                        try {
                            processResponse(jsonResponse.substring(1));
                        } catch (Exception e2) {
                            System.out.println("Reading error "+e2);
                        }
                    }
                    catch (BileteException be) {
                        System.out.println("Reading Bilete error "+be);
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
