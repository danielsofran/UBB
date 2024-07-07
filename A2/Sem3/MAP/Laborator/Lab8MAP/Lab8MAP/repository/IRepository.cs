using Lab8MAP.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.repository
{
    internal interface IRepository<ID, E>
        where ID : IComparable<ID> 
        where E : Entity<ID>
    {
        E? FindOne(ID id);
        ICollection<E> FindAll();
        E? Save(E entity);
        E? Delete(ID id);
    }
}
