import bilete.network.utils.AbstractServer;
import bilete.network.utils.BileteRpcConcurrentServer;
import bilete.server.BileteServerImpl;
import bilete.services.IBileteServices;
import repository.ShowRepository;
import repository.TicketRepository;
import repository.UserRepository;
import repository.db.ShowRepoDB;
import repository.db.TicketRepoDB;
import repository.db.UserRepoDB;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Properties;

public class StartRpcServer {
    private static int defaultPort=55555;
    public static void main(String[] args) {
        // UserRepository userRepo=new UserRepositoryMock();
        Properties serverProps=new Properties();
        try {
            serverProps.load(StartRpcServer.class.getResourceAsStream("/bileteserver.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find bileteserver.properties "+e);
            return;
        }
        UserRepository userRepo=new UserRepoDB(serverProps);
        ShowRepository showRepo=new ShowRepoDB(serverProps);
        TicketRepository ticketRepo=new TicketRepoDB(serverProps);

        IBileteServices bileteServerImpl=new BileteServerImpl(userRepo, showRepo, ticketRepo);
        int bileteServerPort=defaultPort;
        try {
            bileteServerPort = Integer.parseInt(serverProps.getProperty("bilete.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+bileteServerPort);
        AbstractServer server = new BileteRpcConcurrentServer(bileteServerPort, bileteServerImpl);
        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        }finally {
            try {
                server.stop();
            }catch(ServerException e){
                System.err.println("Error stopping server "+e.getMessage());
            }
        }
    }
}
