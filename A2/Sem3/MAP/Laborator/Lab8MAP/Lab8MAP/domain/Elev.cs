using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal class Elev<T> : Entity<T> where T : IComparable<T>
    {
        public string Nume { get; set; }
        public string Scoala { get; set; }

        public Elev(T id, string nume, string scoala) : base(id)
        {
            Nume = nume;
            Scoala = scoala;
        }

        public override string ToString() => $"{Nume}, {Scoala}";
    }
}
