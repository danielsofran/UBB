using bilete.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.services
{
    public interface IBileteObserver
    {
        void bileteUpdated(Ticket ticket);
    }
}
