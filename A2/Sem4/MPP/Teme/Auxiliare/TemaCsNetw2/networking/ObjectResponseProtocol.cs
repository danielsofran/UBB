using bilete.model;
using System;

namespace bilete.network.protocol
{
    public interface Response { }
    public interface UpdateResponse : Response { }

    [Serializable]
    public class OkResponse : Response { }

    [Serializable]
    public class ShowsResponse : OkResponse
    {
        private Show[] shows;
        public ShowsResponse(Show[] shows) { this.shows = shows; }
        public virtual Show[] Shows { get { return shows; } }
    }

    [Serializable]
    public class ErrorResponse : Response
    {
        private string message;
        public ErrorResponse(string message) => this.message = message;
        public virtual string Message { get { return message; } }
    }

    [Serializable]
    public class TicketsUpdateResponse : UpdateResponse
    {
        private Ticket ticket;
        public TicketsUpdateResponse(Ticket ticket) { this.ticket = ticket; }
        public virtual Ticket Ticket { get { return ticket; } }
    }
}