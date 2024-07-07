using bilete.model;
using bilete.network.protocol;
using bilete.services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.CompilerServices;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace bilete.network.client
{
    public class BileteClientWorker : IBileteObserver
    {
        private IBileteServices server;
        private TcpClient connection;

        private NetworkStream stream;
        //private IFormatter formatter;
        private StreamReader sr;
        private BinaryWriter writer;
        JsonSerializerOptions serializerOptions;
        private volatile bool connected;
        
        public BileteClientWorker(IBileteServices server, TcpClient connection)
        {
            //Console.WriteLine("Worker created");
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                sr = new StreamReader(stream);
                writer= new BinaryWriter(stream);
                serializerOptions = new JsonSerializerOptions()
                {
                    //WriteIndented = true,
                    PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
                    Converters = { new JsonStringEnumConverter() }
                };
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
                    Console.WriteLine("AICI");
                    Request request = JsonSerializer.Deserialize<Request>(sr.ReadLine(), serializerOptions);
                    Console.WriteLine($"Request: {request.Type} {request.Data}");
                    Response response = handleRequest((Request)request);
                    Console.WriteLine($"Response: {response}");
                    if (response != null)
                    {
                        sendResponse(response);
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
            Response response = new Response { 
                Type = ResponseType.UPDATE, 
                Data = JsonSerializer.Serialize(ticket, serializerOptions) 
            };
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
            if(request.Type == RequestType.LOGIN)
            { 
                Console.WriteLine("Login request ...");
                User user = JsonSerializer.Deserialize<User>(request.Data.ToString(), serializerOptions);
                try
                {
                    lock(server)
                    {
                        server.login(user, this);
                    }
                    return new Response { Type=ResponseType.OK };
                }
                catch (BileteException ex)
                {
                    connected = false;
                    Console.WriteLine("Client handle request error: "+ex.StackTrace);
                    return new Response { Type=ResponseType.ERROR, Data=ex.Message };
                }
            }
            if(request.Type == RequestType.LOGOUT)
            {
                Console.WriteLine("Logout request ...");
                User user= JsonSerializer.Deserialize<User>(request.Data.ToString(), serializerOptions);
                try
                {
                    lock(server)
                    {
                        server.logout(user, this);
                    }
                    connected= false;
                    return new Response { Type = ResponseType.OK };
                }
                catch(BileteException ex)
                {
                    Console.WriteLine("Client handle request error: " + ex.StackTrace);
                    return new Response { Type = ResponseType.ERROR, Data = ex.Message };
                }
            }
            if(request.Type == RequestType.GET_ARTISTI)
            {
                Console.WriteLine("Get Artisti request ...");
                try
                {
                    Show[] shows;
                    lock(server)
                    {
                        shows = server.findAllArtisti();
                    }
                    return new Response {
                        Type = ResponseType.OK,
                        Data = JsonSerializer.Serialize(shows, serializerOptions)
                    };
                }
                catch(BileteException ex) { return new Response { Type = ResponseType.ERROR, Data = ex.Message }; } 
            }
            if(request.Type == RequestType.GET_SHOWS)
            {
                Console.WriteLine("Get Shows request ...");
                DateOnly date = DateOnly.Parse(request.Data.ToString());
                try
                {
                    Show[] shows;
                    lock(server)
                    {
                        shows = server.findShowsOnDate(date);
                    }
                    return new Response { Type = ResponseType.OK, Data = JsonSerializer.Serialize(shows, serializerOptions) };
                }
                catch (BileteException ex)
                {
                    return new Response { Type = ResponseType.ERROR, Data = ex.Message };
                }
            }
            if(request.Type == RequestType.BUY_TICKET)
            {
                Console.WriteLine("Buy ticket request ...");
                Ticket ticket = JsonSerializer.Deserialize<Ticket>(request.Data.ToString(), serializerOptions); ;
                try
                {
                    lock(server)
                    {
                        server.sellTicket(ticket);
                    }
                    return new Response { Type = ResponseType.OK };
                }
                catch(BileteException ex)
                {
                    return new Response { Type = ResponseType.ERROR, Data = ex.Message };
                }
            }
            return null;
        }

        private void sendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            lock (stream)
            {
                String json = JsonSerializer.Serialize(response, serializerOptions);
                writer.Write(json);
                writer.Write('\n');
                //writer.Flush();
                //stream.Flush();
                Console.WriteLine("Response sent" + json);
            }

        }
    }
}

