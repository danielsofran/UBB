using bilete.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.client
{
    public class TicketEventArgs : EventArgs
    {
        private readonly Ticket ticket;

        public TicketEventArgs(Ticket ticket)
        {
            this.ticket = ticket;
        }

        public Ticket Ticket { get { return ticket; } }
    }
}
