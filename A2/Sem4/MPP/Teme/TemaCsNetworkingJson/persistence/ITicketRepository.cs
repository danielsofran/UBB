using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using bilete.model;

namespace bilete.persistence
{
    public interface ITicketRepository : IRepository<int, Ticket>
    {
    }
}
