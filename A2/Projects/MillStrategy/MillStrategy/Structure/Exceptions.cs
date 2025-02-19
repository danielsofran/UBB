﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MillStrategy.Structure
{
    public class MyException : Exception
    {
        public MyException()
        {
        }

        public MyException(string message)
            : base(message)
        {
        }

        public MyException(string message, Exception inner)
            : base(message, inner)
        {
        }
    }

    public class PozitieException : MyException
    {
        public PozitieException(string message) : base(message)
        {
        }
    }

    public class WrongMoveException : MyException
    {
        public WrongMoveException(string message) : base(message)
        {
        }
    }
}
