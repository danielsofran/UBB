using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.model
{
    [Serializable]
    public class Show : Entity<int>
    {
        int availableSeats = 0, soldSeats = 0;
        private DateTime dateTime;
        public Artist Artist { get; set; }
        public DateOnly Date { get => DateOnly.FromDateTime(dateTime); set => dateTime = value.ToDateTime(BeginTime); }
        public TimeOnly BeginTime { get => TimeOnly.FromDateTime(dateTime); set => dateTime = Date.ToDateTime(value); }
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
