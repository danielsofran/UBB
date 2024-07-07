using Lab8MAP.utils;
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal enum TipJucator
    {
        Rezerva, Participant
    }

    internal class JucatorActiv<T> : Entity<T> where T : IComparable<T>
    {
        public T IdJucator { get => ID; set => ID = value; }
        public T IdMeci { get; set; }
        public int NrPuncteInscrise { get; set; }
        public TipJucator TipJucator { get; set; }

        public JucatorActiv(T idJucator, T idMeci, int nrPuncteInscrie, TipJucator tipJucator)
             : base(idJucator)
        {
            IdMeci = idMeci;
            NrPuncteInscrise = nrPuncteInscrie;
            TipJucator = tipJucator;
        }

        public override bool Equals(object obj)
        {
            if (obj is JucatorActiv<T> jucatorActiv)
                return IdJucator != null && IdJucator.Equals(jucatorActiv.IdJucator) &&
                       IdMeci != null && IdMeci.Equals(jucatorActiv.IdMeci);
            return false;
        }

        public override int GetHashCode()
        {
            return IdJucator.GetHashCode() ^ IdMeci.GetHashCode();
        }

        public override string ToString() => $"{ID}, {IdJucator}, {IdMeci}, {NrPuncteInscrise}, {TipJucator.GetCode()}";
    }
}
