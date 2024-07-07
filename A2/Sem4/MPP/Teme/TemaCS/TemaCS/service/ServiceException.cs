using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.service
{
    internal class ServiceException : ApplicationException
    {
        public ServiceException(string message) : base(message) { }
    }
}
