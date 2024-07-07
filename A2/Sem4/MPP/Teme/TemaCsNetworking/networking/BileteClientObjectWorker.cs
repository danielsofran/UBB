using bilete.model;
using bilete.network.protocol;
using bilete.services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace bilete.network.client
{
    public class BileteClientWorker : IBileteObserver
    {
        private IBileteServices server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;
        
        public BileteClientWorker(IBileteServices server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                formatter= new BinaryFormatter();
                connected = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }

        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = handleRequest((Request)request);
                    if (response != null)
                    {
                        sendResponse((Response)response);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Client Worker error:");
                    Console.WriteLine(ex.StackTrace);
                }

                try { Thread.Sleep(1000); }
                catch (Exception ex) { Console.WriteLine(ex.StackTrace); }
            }
            try
            {
                stream.Close();
                connection.Close();
            }
            catch(Exception ex) { 
                Console.WriteLine("Error closing client worker stream: " + ex.StackTrace); 
            }
        }

        public virtual void bileteUpdated(Ticket ticket)
        {
            Response response = new TicketsUpdateResponse(ticket);
            Console.WriteLine("BileteClientObjectWorker.bileteUpdated "+ticket.CostumerName+" "+ticket.Seats);
            try
            {
                sendResponse(response);
                Console.WriteLine("Response sent");
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine(ex.StackTrace);
            }
        }

        private Response handleRequest(Request request)
        {
            if(request is LoginRequest)
            {
                Console.WriteLine("Login request ...");
                LoginRequest loginRequest = (LoginRequest)request;
                User user = loginRequest.User;
                try
                {
                    lock(server)
                    {
                        server.login(user, this);
                    }
                    return new OkResponse();
                }
                catch (BileteException ex)
                {
                    connected = false;
                    Console.WriteLine("Client handle request error: "+ex.StackTrace);
                    return new ErrorResponse(ex.Message);
                }
            }
            if(request is LogoutRequest)
            {
                Console.WriteLine("Logout request ...");
                LogoutRequest logoutRequest = (LogoutRequest)request;
                User user= logoutRequest.User;
                try
                {
                    lock(server)
                    {
                        server.logout(user, this);
                    }
                    connected= false;
                    return new OkResponse();
                }
                catch(BileteException ex)
                {
                    Console.WriteLine("Client handle request error: " + ex.StackTrace);
                    return new ErrorResponse(ex.Message);
                }
            }
            if(request is GetArtistiRequest)
            {
                Console.WriteLine("Get Artisti request ...");
                GetArtistiRequest getArtistiRequest= (GetArtistiRequest)request;
                try
                {
                    Show[] shows;
                    lock(server)
                    {
                        shows = server.findAllArtisti();
                    }
                    return new ShowsResponse(shows);
                }
                catch(BileteException ex) { return new ErrorResponse(ex.Message); } 
            }
            if(request is GetShowsRequest)
            {
                Console.WriteLine("Get Shows request ...");
                GetShowsRequest showsRequest= (GetShowsRequest)request;
                DateOnly date = showsRequest.Date;
                try
                {
                    Show[] shows;
                    lock(server)
                    {
                        shows = server.findShowsOnDate(date);
                    }
                    return new ShowsResponse(shows);
                }
                catch(BileteException ex)
                {
                    return new ErrorResponse(ex.Message);
                }
            }
            if(request is BuyTicketRequest)
            {
                Console.WriteLine("Buy ticket request ...");
                BuyTicketRequest buyTicketRequest = (BuyTicketRequest)request;
                Ticket ticket = buyTicketRequest.Ticket;
                try
                {
                    lock(server)
                    {
                        server.sellTicket(ticket);
                    }
                    return new OkResponse();
                }
                catch(BileteException ex)
                {
                    return new ErrorResponse(ex.Message);
                }
            }
            return null;
        }

        private void sendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            lock (stream)
            {
                formatter.Serialize(stream, response);
                stream.Flush();
            }

        }
    }
}

