using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.services
{
    public class BileteException : Exception
    {
        public BileteException() : base() { }

        public BileteException(string message) : base(message) { }

        public BileteException(string message, Exception innerException) : base(message, innerException) { }
    }
}
