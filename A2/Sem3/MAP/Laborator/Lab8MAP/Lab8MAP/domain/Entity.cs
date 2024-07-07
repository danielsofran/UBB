using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal class Entity<T> : IEquatable<Entity<T>>, IEqualityComparer<Entity<T>>, IComparable<Entity<T>> where T : IComparable<T>
    {
        public T ID { get; set; }

        public Entity(T id)
        {
            ID = id;
        }

        public bool Equals(Entity<T>? other) => other != null && other.ID != null && other.ID.Equals(ID);

        public bool Equals(Entity<T>? x, Entity<T>? y) => 
            x == null && y == null || x != null && y != null && Equals(x.ID, y.ID);

        public int GetHashCode([DisallowNull] Entity<T> obj) => obj.ID.GetHashCode();

        public int CompareTo(Entity<T>? other) => 
            (other == null) ? -1 : other.ID.CompareTo(ID);

        public override bool Equals(object obj)
        {
            if (obj is Entity<T> entity)
                return Equals(entity);
            return false;
        }

        public override int GetHashCode()
        {
            return ID.GetHashCode();
        }

        public override string? ToString()
        {
            return ID?.ToString();
        }
    }
}
