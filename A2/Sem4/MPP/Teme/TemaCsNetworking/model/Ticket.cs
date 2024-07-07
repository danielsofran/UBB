using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.model
{
    [Serializable]
    public class Ticket : Entity<int>
    {
        public Show Show { get; set; }
        public string CostumerName { get; set; }
        public int Seats { get; set;}

        public Ticket(Show show, string costumerName, int seats) : base(-1)
        {
            Show = show;
            CostumerName = costumerName;
            Seats = seats;
        }

        public Ticket(int ID, Show show, string costumerName, int seats) : base(ID)
        {
            Show = show;
            CostumerName = costumerName;
            Seats = seats;
        }
    }
}
