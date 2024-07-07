using bilete.model;
using bilete.network.client;
using bilete.network.utils;
using bilete.persistence;
using bilete.services;
using chat.server;
using networking;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace bilete.server
{
    class SerialBileteServer : ConcurrentServer
    {
        private IBileteServices server;
        private BileteClientWorker worker;

        public SerialBileteServer(string host, int port, IBileteServices server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialBileteServer ...");
        }

        protected override Thread createWorker(TcpClient client)
        {
            worker = new BileteClientWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }

    public class Program
    {
        static void serialize_Test()
        {
            ITicketRepository ticketRepository = new TicketRepository();
            Ticket first = ticketRepository.FindAll().First();
            Console.WriteLine(first);
            // SERIALIZE TEST
            using (Stream stream = new MemoryStream())
            {
                IFormatter formatter = new BinaryFormatter();
                lock (stream)
                {
                    formatter.Serialize(stream, first);
                    stream.Flush();
                }
                stream.Position = 0;
                object obj = formatter.Deserialize(stream);
                Ticket ticket = (Ticket)obj;
                Console.WriteLine(ticket);
            }
        }
        
        public static void Main(string[] args)
        {
            IUserRepository userRepository = new UserRepository();
            IShowRepository showRepository = new ShowRepository();
            ITicketRepository ticketRepository = new TicketRepository();

            IBileteServices serviceImpl = new BileteServerImpl(userRepository, showRepository, ticketRepository);
            SerialBileteServer server = new SerialBileteServer("127.0.0.1", Ports.Server, serviceImpl);

            Console.WriteLine($"Server started on port {Ports.Server}...");
            Console.WriteLine("Press <enter> to exit ...");
            server.Start();
            
            Console.ReadLine();
        }
    }
}
