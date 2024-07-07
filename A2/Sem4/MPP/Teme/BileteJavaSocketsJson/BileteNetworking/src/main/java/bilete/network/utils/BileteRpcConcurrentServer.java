package bilete.network.utils;

import bilete.network.rpc.BileteClientRpcWorker;
import bilete.services.IBileteServices;

import java.net.Socket;

public class BileteRpcConcurrentServer extends AbsConcurrentServer {
    private IBileteServices bileteServer;
    public BileteRpcConcurrentServer(int port, IBileteServices chatServer) {
        super(port);
        this.bileteServer = chatServer;
        System.out.println("Bilete- BileteRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        BileteClientRpcWorker worker=new BileteClientRpcWorker(bileteServer, client);

        Thread tw=new Thread(worker);
        return tw;
    }

    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}