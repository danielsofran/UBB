using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.exceptions
{
    internal class ParsingException : ApplicationException
    {
        public ParsingException(string message) : base(message) { }
    }
}
