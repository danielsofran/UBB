using bilete.model;
using System;

namespace bilete.network.protocol
{
    public interface Request { }

    [Serializable]
    public class LoginRequest : Request
    {
        private User user;

        public LoginRequest(User user) { this.user = user; }

        public virtual User User { get { return user; } }
    }

    [Serializable]
    public class LogoutRequest : Request 
    {
        private User user;
        public LogoutRequest(User user) { this.user= user; }
        public virtual User User { get { return user;} }
    }

    [Serializable]
    public class GetArtistiRequest : Request
    {
        public GetArtistiRequest() { }
    }

    [Serializable]
    public class GetShowsRequest : Request
    {
        private DateOnly date;
        public GetShowsRequest(DateOnly date) { this.date = date; }
        public virtual DateOnly Date { get => date; }
    }

    [Serializable]
    public class BuyTicketRequest : Request
    {
        private Ticket ticket;
        public BuyTicketRequest(Ticket ticket) { this.ticket = ticket; }
        public virtual Ticket Ticket { get { return this.ticket; } }
    }
}