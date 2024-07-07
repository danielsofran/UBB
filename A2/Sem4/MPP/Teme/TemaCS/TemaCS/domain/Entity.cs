using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.domain
{
    internal class Entity<T> : IEquatable<Entity<T>>, IEqualityComparer<Entity<T>>
    {
        public T ID { get; set; }

        public Entity(T id)
        { 
            this.ID = id; 
        }

        public bool Equals(Entity<T>? other) => other != null && other.ID != null && other.ID.Equals(ID);

        public bool Equals(Entity<T>? x, Entity<T>? y) => 
            x == null && y == null || x != null && y != null && Equals(x.ID, y.ID);

        public int GetHashCode([DisallowNull] Entity<T> obj) => obj.ID.GetHashCode();

        public override string ToString() => $"ID: {ID}";
    }
}
