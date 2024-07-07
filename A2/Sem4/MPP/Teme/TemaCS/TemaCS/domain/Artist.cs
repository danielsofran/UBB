using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.domain
{
    internal class Artist : Entity<int>
    {
        public string Name { get; set; }

        public Artist(int ID, string name) : base(ID) { Name = name; }

        public override string ToString() { return $"{Name}"; }
    }
}
