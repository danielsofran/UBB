using bilete.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.services
{
    public interface IBileteServices
    {
        void login(User user, IBileteObserver client);
        void logout(User user, IBileteObserver client);
        Show[] findAllArtisti();
        Show[] findShowsOnDate(DateOnly date);
        void sellTicket(Ticket ticket);
    }
}
