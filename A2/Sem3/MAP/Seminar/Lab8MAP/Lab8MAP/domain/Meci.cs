using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal class Meci<T> : Entity<T> where T : IComparable<T>
    {
        public Echipa<T> Echipa1 { get; set; }
        public Echipa<T> Echipa2 { get; set; }
        public DateTime Data { get; set; }

        public Meci(T id, Echipa<T> echipa1, Echipa<T> echipa2, DateTime data) : base(id)
        {
            Echipa1 = echipa1;
            Echipa2 = echipa2;
            Data = data;
        }

        public override string ToString() => $"{Echipa1.Nume} VS {Echipa2.Nume} ({Data.ToString("dd.MM.yyyy")})";
    }
}
