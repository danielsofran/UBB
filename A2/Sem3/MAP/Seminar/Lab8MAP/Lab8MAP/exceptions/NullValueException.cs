using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.exceptions
{
    internal class NullValueException : ApplicationException
    {
        public NullValueException(string Field, string Class) : 
            base(string.Format("NullValueException on Field {0}, in Class {1}", Field, Class)){}
    }
}
