using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.exceptions
{
    internal class UIException : ApplicationException
    {
        public UIException(string msg = "") : base(msg) { }
    }
}
