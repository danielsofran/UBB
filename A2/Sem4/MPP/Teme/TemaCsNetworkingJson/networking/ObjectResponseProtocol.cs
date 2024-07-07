using bilete.model;
using System;

namespace bilete.network.protocol
{
    public enum ResponseType
    {
        OK, ERROR, UPDATE
    }

    public class Response { 
        public ResponseType Type { get; set; }
        public Object? Data { get; set; }
    }

}