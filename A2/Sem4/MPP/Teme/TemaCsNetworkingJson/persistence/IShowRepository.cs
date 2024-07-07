using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using bilete.model;

namespace bilete.persistence
{
    public interface IShowRepository : IRepository<int, Show>
    {
        IEnumerable<Show> FindByDay(DateOnly date);
    }
}
