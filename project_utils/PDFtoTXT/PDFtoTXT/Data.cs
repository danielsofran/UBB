using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PDFtoTXT
{
    internal class DataPart
    {
        public string Program { get; set; }
        public string MachineTime { get; set; }
        public string Greutate { get; set; }
        public string Suprafata { get; set; }
        public KeyValuePair<string, string> Dimensiune { get; set; }
    }

    internal class Data
    {
        public List<DataPart> Parts { get; set; }
        public string Material { get; set; }
        public string Grosime { get; set; }
    }
}
