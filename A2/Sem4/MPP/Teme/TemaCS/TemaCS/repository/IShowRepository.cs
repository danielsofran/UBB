using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaCS.domain;

namespace TemaCS.repository
{
    internal interface IShowRepository : IRepository<int, Show>
    {
        IEnumerable<Show> FindByDay(DateOnly date);
    }
}
