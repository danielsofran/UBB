using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.model
{
    [Serializable]
    public class Artist : Entity<int>
    {
        [JsonProperty("name")]
        public string Name { get; set; }

        public Artist(int ID, string name) : base(ID) { Name = name; }

        public override string ToString() { return $"{Name}"; }
    }
}
