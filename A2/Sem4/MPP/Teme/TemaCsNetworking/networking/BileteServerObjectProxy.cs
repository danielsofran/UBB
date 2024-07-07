using bilete.network.protocol;
using bilete.services;
using bilete.model;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;

namespace bilete.network.server
{
    public class BileteServerProxy : IBileteServices
    {
        private string host;
        private int port;

        private IBileteObserver client;

        private NetworkStream stream;

        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle _waitHandle;

        public BileteServerProxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses= new Queue<Response>();
            
        }

        public virtual void login(User user, IBileteObserver client)
        {
            initializeConnection();
            Request request = new LoginRequest(user);
            sendRequest(request);
            Response response = readResponse();
            if (response is OkResponse)
            {
                this.client = client;
                return;
            }
            if(response is ErrorResponse)
            {
                ErrorResponse errorResponse = (ErrorResponse)response;
                closeConnection();
                throw new BileteException("ErrorResponse in login: "+errorResponse.Message);
            }
        }

        public virtual void logout(User user, IBileteObserver client)
        {
            Request request = new LogoutRequest(user);
            sendRequest(request);
            Response response = readResponse();
            closeConnection();
            if(response is ErrorResponse)
            {
                ErrorResponse errorResponse= (ErrorResponse)response;
                throw new BileteException("ErrorResponse in logout: " + errorResponse.Message);
            }
        }

        public virtual Show[] findAllArtisti()
        {
            Request request = new GetArtistiRequest();
            sendRequest(request);
            Response response = readResponse();
            if(response is ErrorResponse) { 
                ErrorResponse errorResponse= (ErrorResponse)response;
                throw new BileteException("ErrorResponse in findAllArtisti: " + errorResponse.Message);
            }
            ShowsResponse showsResponse = (ShowsResponse)response;
            Show[] shows = showsResponse.Shows;
            return shows;
        }

        public virtual Show[] findShowsOnDate(DateOnly date)
        {
            Request request = new GetShowsRequest(date); 
            sendRequest(request);
            Response response = readResponse();
            if(response is ErrorResponse)
            {
                ErrorResponse errorResponse= (ErrorResponse)response;
                throw new BileteException("ErrorResponse in find on date: "+errorResponse.Message);
            }
            ShowsResponse showsResponse = (ShowsResponse)response;
            Show[] shows = showsResponse.Shows;
            return shows;
        }

        public virtual void sellTicket(Ticket ticket)
        {
            Request request = new BuyTicketRequest(ticket);
            sendRequest(request);
            Response response = readResponse();
            if(response is ErrorResponse)
            {
                ErrorResponse errorResponse= (ErrorResponse)response;
                throw new BileteException("ErrorResponse in sell ticket: "+errorResponse.Message) ;
            }
        }

        #region Ctrl C Ctrl V
        private void closeConnection()
        {
            finished = true;
            try
            {
                stream.Close();

                connection.Close();
                _waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }

        private void sendRequest(Request request)
        {
            try
            {
                formatter.Serialize(stream, request); // #ERROR
                stream.Flush();
            }
            catch (Exception e)
            {
                throw new BileteException("Error sending object " + e);
            }

        }

        private Response readResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (responses)
                {
                    //Monitor.Wait(responses); 
                    response = responses.Dequeue();

                }


            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return response;
        }
        private void initializeConnection()
        {
            try
            {
                connection = new TcpClient(host, port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                _waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
                throw new Exception("initializeConnection", e);
                
            }
        }
        private void startReader()
        {
            Thread tw = new Thread(run);
            tw.Start();
        }
        #endregion

        private void handleUpdate(UpdateResponse update)
        {
            if(update is TicketsUpdateResponse)
            {
                TicketsUpdateResponse response = (TicketsUpdateResponse)update;
                Ticket ticket = response.Ticket;
                Console.WriteLine("Server Proxy Ticket update " + ticket);
                try
                {
                    client.bileteUpdated(ticket);
                }
                catch (BileteException e) { Console.WriteLine(e.StackTrace); }
            }
        }

        public virtual void run()
        {
            while (!finished)
            {
                try
                {
                    object response = formatter.Deserialize(stream);
                    Console.WriteLine("response received " + response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate((UpdateResponse)response);
                    }
                    else
                    {

                        lock (responses)
                        {


                            responses.Enqueue((Response)response);

                        }
                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error " + e);
                }

            }
        }
    }
}
