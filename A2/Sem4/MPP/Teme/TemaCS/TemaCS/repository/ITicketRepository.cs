using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaCS.domain;

namespace TemaCS.repository
{
    internal interface ITicketRepository : IRepository<int, Ticket>
    {
    }
}
