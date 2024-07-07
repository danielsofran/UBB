using bilete.model;
using bilete.persistence;
using bilete.services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace chat.server
{
    public class BileteServerImpl : IBileteServices
    {
        private IUserRepository userRepository;
        private IShowRepository showRepository;
        private ITicketRepository ticketRepository;
        private readonly IDictionary<string, IBileteObserver> loggedClients;

        public BileteServerImpl(IUserRepository userRepository, IShowRepository showRepository, ITicketRepository ticketRepository)
        {
            this.userRepository = userRepository;
            this.showRepository = showRepository;
            this.ticketRepository = ticketRepository;
            this.loggedClients = new Dictionary<string, IBileteObserver>();
        }

        public void login(User user, IBileteObserver client)
        {
            User userR = userRepository.Find(user.Username, user.Password);
            if (userR != null)
            {
                if (loggedClients.ContainsKey(user.Username))
                    throw new BileteException("User already logged in!");
                loggedClients[user.Username] = client;
            }
            else 
                throw new BileteException("Authentication failed.");
        }

        public void logout(User user, IBileteObserver client)
        {
            IBileteObserver localClient = loggedClients[user.Username];
            if (localClient == null)
                throw new BileteException($"User {user.Username} is not logged in!");
            loggedClients.Remove(user.Username);
        }

        public Show[] findAllArtisti()
        {
            IEnumerable<Show> shows = showRepository.FindAll();
            return shows.ToArray();
        }

        public Show[] findShowsOnDate(DateOnly date)
        {
            IEnumerable<Show> shows = showRepository.FindByDay(date);
            return shows.ToArray();
        }

        public void sellTicket(Ticket ticket)
        {
            if (ticket.Show == null || ticket.Show.Artist == null || ticket.CostumerName == null || ticket.CostumerName.Length == 0 || ticket.Seats <= 0)
            {
                throw new BileteException("Ticket invalid!");
            }
            if (ticket.Show.AvailableSeats < ticket.Seats)
            {
                throw new BileteException("Not enough seats!");
            }
            ticketRepository.Add(ticket);
            ticket.Show.AvailableSeats -= ticket.Seats;
            ticket.Show.SoldSeats += ticket.Seats;
            showRepository.Update(ticket.Show.ID, ticket.Show);

            Console.WriteLine("Notifying clients ...");
            foreach(IBileteObserver client in loggedClients.Values)
                if(client != null)
                {
                    try
                    {
                        Console.WriteLine("Notifying client: " + client);
                        Task.Run(() => {
                            client.bileteUpdated(ticket);
                            Console.WriteLine($"Client {client} notified.");
                        });
                    }
                    catch(BileteException ex)
                    {
                        Console.WriteLine("Error notifying client " + client + ": " + ex.StackTrace);
                    }
                }
        }
    }
}
