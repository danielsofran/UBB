using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using bilete.model;

namespace bilete.persistence
{
    public class RepositoryException : ApplicationException
    {
        public RepositoryException() { }
        public RepositoryException(String mess) : base(mess) { }
        public RepositoryException(String mess, Exception e) : base(mess, e) { }
    }

    public interface IRepository<ID, E> where E : Entity<ID>
    { 
        void Add(E entity);
        void Remove(ID id);
        void Update(ID id, E entity);
        E Find(ID id);
        IEnumerable<E> FindAll();
    }
}
