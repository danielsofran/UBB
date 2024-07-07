using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaCS.domain;
using TemaCS.repository;

namespace TemaCS.service
{
    internal class Service
    {
        internal IUserRepository UserRepository { private get; set; }
        internal IShowRepository ShowRepository { private get; set; }
        internal ITicketRepository TicketRepository { private get; set; }

        public Service() { }

        public User Login(string username, string password)
        {
            User user = UserRepository.Find(username, password);
            if (user == null)
            {
                throw new ServiceException($"User with username: {username} not found!");
            }
            return user;
        }

        public IEnumerable<Show> FindArtisti()
        {
            return ShowRepository.FindAll();
        }

        public IEnumerable<Show> FindShowsOnDate(DateTime dateTime)
        {
            return ShowRepository.FindByDay(DateOnly.FromDateTime(dateTime));
        }

        public void SellTicket(Show show, int seats, string costumer_name)
        {
            if (show == null || show.Artist == null || costumer_name == null || costumer_name.Length == 0 || seats <= 0)
            {
                throw new ServiceException("Ticket invalid!");
            }
            Ticket ticket = new Ticket(show, costumer_name, seats);
            if(show.AvailableSeats < seats)
            {
                throw new ServiceException("Not enough seats!");
            }
            TicketRepository.Add(ticket);
            show.AvailableSeats -= seats;
            show.SoldSeats += seats;
            ShowRepository.Update(show.ID, show);
        }
    }
}
