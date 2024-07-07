using Lab8MAP.exceptions;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.domain
{
    internal class Echipa<T> : Entity<T> where T : IComparable<T>
    {
        public string Nume { get; set; }

        public Echipa(T id, string nume) : base(id)
        {
            Nume = nume;
        }

        public override string ToString() => Nume;

    }
}
