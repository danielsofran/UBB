﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using networking;

namespace bilete.network.utils
{
    public abstract class AbstractServer
    {
        private TcpListener server;
        private String host = "127.0.0.1";
        private int port = Ports.Server;

        public AbstractServer(String host, int port)
        {
            this.host = host;
            this.port = port;
        }

        public void Start()
        {
            IPAddress adr = IPAddress.Parse(host);
            IPEndPoint ep = new IPEndPoint(adr, port);
            server = new TcpListener(ep);
            server.Start();
            while (true)
            {
                Console.WriteLine("Waiting for clients ...");
                TcpClient client = server.AcceptTcpClient();
                Console.WriteLine("Client connected ...");
                processRequest(client);
            }
        }

        public abstract void processRequest(TcpClient client);
    }


    public abstract class ConcurrentServer : AbstractServer
    {

        public ConcurrentServer(string host, int port) : base(host, port) { }

        public override void processRequest(TcpClient client)
        {
            Thread t = createWorker(client);
            t.Start();
        }

        protected abstract Thread createWorker(TcpClient client);
    }
}
