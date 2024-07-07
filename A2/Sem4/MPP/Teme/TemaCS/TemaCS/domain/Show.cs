using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.domain
{
    internal class Show : Entity<int>
    {
        int availableSeats = 0, soldSeats = 0;
        public Artist Artist { get; set; }
        public DateOnly Date { get; set; }
        public TimeOnly BeginTime { get; set; }
        public string Location { get; set; }
        public int AvailableSeats { get => availableSeats; set { availableSeats = value; } }
        public int SoldSeats { get => soldSeats; set { soldSeats = value; } }

        public Show(int ID, Artist artist, DateOnly date, TimeOnly beginTime, string location, int availableSeats, int soldSeats) : base(ID)
        {
            AvailableSeats = availableSeats;
            SoldSeats = soldSeats;
            Artist = artist;
            Date = date;
            BeginTime = beginTime;
            Location = location;
            AvailableSeats = availableSeats;
            SoldSeats = soldSeats;
        }
    }
}
