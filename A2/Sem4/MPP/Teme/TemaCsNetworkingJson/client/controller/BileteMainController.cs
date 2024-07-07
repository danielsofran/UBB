using bilete.client;
using bilete.model;
using bilete.services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace client.controller
{
    public class BileteMainController : IBileteObserver
    {
        public event EventHandler<TicketEventArgs> TicketSold;
        private readonly IBileteServices server;
        private User? currentUser;

        public BileteMainController(IBileteServices server)
        {
            this.server = server;
            currentUser = null;
        }

        public void login(string username, string password)
        {
            User user = new User(username, password);
            server.login(user, this);
            Console.WriteLine("Login succeeded ...");
            currentUser = user;
            Console.WriteLine("Current user {0}", user);
        }

        public void logout()
        {
            Console.WriteLine("Ctrl logout");
            try { server.logout(currentUser, this); }
            catch { }
            //server.logout(currentUser, this);
            currentUser = null;
        }

        public void bileteUpdated(Ticket ticket)
        {
            Console.WriteLine("Bilete updated: " + ticket);
            TicketEventArgs ticketEventArgs = new TicketEventArgs(ticket);
            TicketSold(this, ticketEventArgs);
            Console.WriteLine("Update Event called");
        }

        public IEnumerable<Show> findAllArtisti()
        {
            //Console.WriteLine("Finding all artisti ...");
            return server.findAllArtisti();
        }

        public IEnumerable<Show> findShowsOnDate(DateTime datetime)
        {
            DateOnly date = DateOnly.FromDateTime(datetime);
            return server.findShowsOnDate(date);
        }

        public void sellTicket(Ticket ticket)
        {
            // nu dau update aici, pentru ca inca nu s-a actualizat server ul
            server.sellTicket(ticket);
        }
    }
}
