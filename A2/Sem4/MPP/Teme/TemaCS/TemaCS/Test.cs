using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaCS.domain;
using TemaCS.repository;

namespace TemaCS
{
    internal class Test
    {
        static void testBD(IDictionary<String, string> props)
        {
            int nr_run = 8 + 6;

            Artist artist = new Artist(4, "Andrei");
            Artist artist_nou = new Artist(nr_run+5, "Vasile");
            ArtistRepository artistRepository = new ArtistRepository(props);
            Debug.Assert(artistRepository.FindAll().Any());
            artistRepository.Add(artist_nou);
            Debug.Assert(artistRepository.FindAll().Count() == 2);
            Debug.Assert(artistRepository.Find(artist_nou.ID) != null);
            artistRepository.Remove(artist_nou.ID);
            Debug.Assert(artistRepository.FindAll().Count() == 1);

            Show show = new Show(3, artist, DateOnly.FromDateTime(DateTime.Now), TimeOnly.FromDateTime(DateTime.Now), "Africa", 50, 50);
            Show show2 = new Show(nr_run+3, artist, DateOnly.FromDateTime(DateTime.Now), TimeOnly.FromDateTime(DateTime.Now), "Bistrita", 100, 100);
            ShowRepository showRepository = new ShowRepository(props);
            Debug.Assert(showRepository.FindAll().Any());
            showRepository.Add(show2);
            Debug.Assert(showRepository.FindAll().Count() == 2);
            Debug.Assert(showRepository.Find(show2.ID) != null);
            showRepository.Remove(show2.ID);
            Debug.Assert(showRepository.FindAll().Count() == 1);

            Ticket ticket = new Ticket(3, show, "Daniel", 4);
            Ticket ticket2 = new Ticket(nr_run, show, "Vasile", 5);
            TicketRepository ticketRepository = new TicketRepository(props);
            Debug.Assert(showRepository.FindAll().Any());
            ticketRepository.Add(ticket2);
            Debug.Assert(ticketRepository.FindAll().Count() == 2);
            Debug.Assert(ticketRepository.Find(ticket2.ID) != null);
            ticketRepository.Remove(ticket2.ID);
            Debug.Assert(ticketRepository.FindAll().Count() == 1);
        }
        public static void Run(IDictionary<String, string> props)
        {
            testBD(props);
        }
    }
}
