using log4net;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using bilete.model;

namespace bilete.persistence
{
    public class TicketRepository : ITicketRepository
    {
        private static readonly ILog log = LogManager.GetLogger("TicketRepository");

        public TicketRepository()
        {
        }

        public void Add(Ticket entity)
        {
            var con = DBUtils.getConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into tickets(show_id, costumername, seats) values (@show_id, @costumername, @seats)";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@show_id";
                paramId.Value = entity.Show.ID;
                comm.Parameters.Add(paramId);

                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@costumername";
                paramName.Value = entity.CostumerName;
                comm.Parameters.Add(paramName);

                var paramSeats = comm.CreateParameter();
                paramSeats.ParameterName = "@seats";
                paramSeats.Value = entity.Seats;
                comm.Parameters.Add(paramSeats);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No ticket added !");
                log.Info("Ticket added!");
            }
        }

        public void Remove(int id)
        {
            IDbConnection con = DBUtils.getConnection();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "delete from tickets where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No ticket deleted!");
                log.Info("Ticket Removed!");
            }
        }

        public void Update(int id, Ticket entity)
        {
            IDbConnection con = DBUtils.getConnection();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "update tickets set show_id=@show_id, costumername=@costumername, seats=@seats where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                IDbDataParameter paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@costumername";
                paramNume.Value = entity.CostumerName;
                comm.Parameters.Add(paramNume);

                IDbDataParameter paramShow = comm.CreateParameter();
                paramShow.ParameterName = "@show_id";
                paramShow.Value = entity.Show.ID;
                comm.Parameters.Add(paramShow);

                IDbDataParameter paramSeats = comm.CreateParameter();
                paramSeats.ParameterName = "@seats";
                paramSeats.Value = entity.Seats;
                comm.Parameters.Add(paramSeats);

                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No ticket updated!");
                log.Info("Ticket updated!");
            }
        }

        public Ticket Find(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from tickets_shows where ticketid=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        string costumername = dataR.GetString(2);
                        int seats = dataR.GetInt32(3);

                        int artistid = dataR.GetInt32(4);
                        String nume = dataR.GetString(5);
                        Artist artist = new Artist(artistid, nume);

                        int showid = dataR.GetInt32(6);
                        DateTime dateTime = dataR.GetDateTime(7);
                        string location = dataR.GetString(8);
                        int availableseats = dataR.GetInt32(9);
                        int soldseats = dataR.GetInt32(10);
                        Show show = new Show(id, artist, DateOnly.FromDateTime(dateTime), TimeOnly.FromDateTime(dateTime), location, availableseats, soldseats);

                        Ticket ticket = new Ticket(id, show, costumername, seats);
                        log.InfoFormat("Exiting findOne with value {0}", ticket);
                        return ticket;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Ticket> FindAll()
        {
            IDbConnection con = DBUtils.getConnection();
            IList<Ticket> tasksR = new List<Ticket>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from tickets_shows";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        string costumername = dataR.GetString(2);
                        int seats = dataR.GetInt32(3);

                        int artistid = dataR.GetInt32(4);
                        String nume = dataR.GetString(5);
                        Artist artist = new Artist(artistid, nume);

                        int showid = dataR.GetInt32(6);
                        DateTime dateTime = dataR.GetDateTime(7);
                        string location = dataR.GetString(8);
                        int availableseats = dataR.GetInt32(9);
                        int soldseats = dataR.GetInt32(10);
                        Show show = new Show(id, artist, DateOnly.FromDateTime(dateTime), TimeOnly.FromDateTime(dateTime), location, availableseats, soldseats);

                        Ticket ticket = new Ticket(id, show, costumername, seats);

                        tasksR.Add(ticket);
                    }
                }
            }
            log.Info("Found all tickets");
            return tasksR;
        }
    }
}
