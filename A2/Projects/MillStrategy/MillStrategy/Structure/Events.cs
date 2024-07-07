using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MillStrategy.Structure
{
    public class PozitieEventArgs : EventArgs
    {
        public IPozitie Pozitie { get; set; }
    }

    public class PozitiiEventArgs : EventArgs
    {
        public IPozitii Pozitii { get; set; }
    }
}
