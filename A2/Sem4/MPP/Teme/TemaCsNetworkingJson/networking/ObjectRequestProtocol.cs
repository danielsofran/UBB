using bilete.model;
using System;

namespace bilete.network.protocol
{
    public enum RequestType
    {
        LOGIN, LOGOUT, GET_ARTISTI, GET_SHOWS, BUY_TICKET
    }

    public class Request {
        public RequestType Type { get; set; }
        public Object? Data { get; set; }
    }
}