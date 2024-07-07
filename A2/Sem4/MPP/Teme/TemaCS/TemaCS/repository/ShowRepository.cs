using log4net;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TemaCS.domain;

namespace TemaCS.repository
{
    internal class ShowRepository : IShowRepository
    {
        IDictionary<String, string> props;
        private static readonly ILog log = LogManager.GetLogger("ShowRepository");

        public ShowRepository(IDictionary<String, string> props)
        {
            log.Info("ArtistRepo Constructor");
            this.props = props;
        }

        public void Add(Show entity)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into shows(artist_id, datetime, location, availableseats, soldseats) values (@artist_id,@datetime,@location,@availableseats,@soldseats)";
                
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@artist_id";
                paramId.Value = entity.Artist.ID;
                comm.Parameters.Add(paramId);

                var paramDateTime = comm.CreateParameter();
                paramDateTime.ParameterName = "@datetime";
                paramDateTime.Value = entity.Date.ToDateTime(entity.BeginTime);
                comm.Parameters.Add(paramDateTime);

                var paramLoc = comm.CreateParameter();
                paramLoc.ParameterName = "@location";
                paramLoc.Value = entity.Location;
                comm.Parameters.Add(paramLoc);

                var paramAS = comm.CreateParameter();
                paramAS.ParameterName = "@availableseats";
                paramAS.Value = entity.AvailableSeats;
                comm.Parameters.Add(paramAS);

                var paramSS = comm.CreateParameter();
                paramSS.ParameterName = "@soldseats";
                paramSS.Value = entity.SoldSeats;
                comm.Parameters.Add(paramSS);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No show added !");
                log.Info("Show added!");
            }
        }

        public void Remove(int id)
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "delete from shows where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No show deleted!");
                log.Info("Show removed!");
            }
        }

        public void Update(int id, Show entity)
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "update shows set artist_id=@artist_id, datetime=@datetime, location=@location, availableseats=@availableseats, soldseats=@soldseats where id=@id";
                IDbDataParameter paramIdS = comm.CreateParameter();
                paramIdS.ParameterName = "@id";
                paramIdS.Value = id;
                comm.Parameters.Add(paramIdS);

                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@artist_id";
                paramId.Value = entity.Artist.ID;
                comm.Parameters.Add(paramId);

                var paramDate = comm.CreateParameter();
                paramDate.ParameterName = "@datetime";
                paramDate.Value = entity.Date;
                comm.Parameters.Add(paramDate);

                var paramLoc = comm.CreateParameter();
                paramLoc.ParameterName = "@location";
                paramLoc.Value = entity.Location;
                comm.Parameters.Add(paramLoc);

                var paramAS = comm.CreateParameter();
                paramAS.ParameterName = "@availableseats";
                paramAS.Value = entity.AvailableSeats;
                comm.Parameters.Add(paramAS);

                var paramSS = comm.CreateParameter();
                paramSS.ParameterName = "@soldseats";
                paramSS.Value = entity.SoldSeats;
                comm.Parameters.Add(paramSS);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No shows updated!");
                log.Info("Show updated!");
            }
        }

        public Show Find(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists_shows where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int artistid = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        Artist artist = new Artist(artistid, nume);

                        DateTime datetime = dataR.GetDateTime(3);
                        string location = dataR.GetString(4);
                        int availableseats = dataR.GetInt32(5);
                        int soldseats = dataR.GetInt32(6);

                        Show show = new Show(id, artist, DateOnly.FromDateTime(datetime), TimeOnly.FromDateTime(datetime), location, availableseats, soldseats);
                        log.InfoFormat("Exiting findOne with value {0}", artist);
                        return show;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Show> FindAll()
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Show> tasksR = new List<Show>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists_shows";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int artistid = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        Artist artist = new Artist(artistid, nume);

                        int id = dataR.GetInt32(2);
                        DateTime datetime = dataR.GetDateTime(3);
                        string location = dataR.GetString(4);
                        int availableseats = dataR.GetInt32(5);
                        int soldseats = dataR.GetInt32(6);

                        Show show = new Show(id, artist, DateOnly.FromDateTime(datetime), TimeOnly.FromDateTime(datetime), location, availableseats, soldseats);
                        tasksR.Add(show);
                    }
                }
            }
            log.Info("Found all shows!");
            return tasksR;
        }

        public IEnumerable<Show> FindByDay(DateOnly date)
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<Show> tasksR = new List<Show>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists_shows where DATE(datetime)=@date";
                IDbDataParameter paramDate = comm.CreateParameter();
                paramDate.ParameterName = "@date";
                paramDate.Value = date;
                comm.Parameters.Add(paramDate);

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int artistid = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        Artist artist = new Artist(artistid, nume);

                        int id = dataR.GetInt32(2);
                        DateTime dateTime= dataR.GetDateTime(3);
                        string location = dataR.GetString(4);
                        int availableseats = dataR.GetInt32(5);
                        int soldseats = dataR.GetInt32(6);

                        Show show = new Show(id, artist, date, TimeOnly.FromDateTime(dateTime), location, availableseats, soldseats);
                        tasksR.Add(show);
                    }
                }
            }
            log.InfoFormat("Found all shows in date {0}!", date);
            return tasksR;
        }
    }
}
