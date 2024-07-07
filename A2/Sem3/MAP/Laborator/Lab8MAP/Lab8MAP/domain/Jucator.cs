using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal class Jucator<T> : Elev<T> where T : IComparable<T>
    {
        public Echipa<T> Echipa { get; set; }
        public Jucator(T id, string nume, string scoala, Echipa<T> echipa) : base(id, nume, scoala)
        {
            Echipa = echipa;
        }

        public override string ToString() => $"{Nume}: {Echipa.Nume}";
    }
}
