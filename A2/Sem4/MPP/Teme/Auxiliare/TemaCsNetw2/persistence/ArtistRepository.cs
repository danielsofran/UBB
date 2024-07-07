using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using log4net;
using bilete.model;

namespace bilete.persistence
{
    
    public class ArtistRepository : IArtistRepository
    {
        private static readonly ILog log = LogManager.GetLogger("ArtistRepository");

        public ArtistRepository()
        {
            log.Info("ArtistRepo Constructor");
        }

        public void Add(Artist entity)
        {
            var con = DBUtils.getConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into artists(name) values (@name)";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@name";
                paramId.Value = entity.Name;
                comm.Parameters.Add(paramId);
                
                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No artist added !");
                log.Info("Artist added!");
            }
        }

        public Artist Find(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String nume=dataR.GetString(1);
                        Artist artist = new Artist(id, nume);
                        log.InfoFormat("Exiting findOne with value {0}", artist);
                        return artist;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Artist> FindAll()
        {
            IDbConnection con = DBUtils.getConnection();
            IList<Artist> tasksR = new List<Artist>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        Artist artist = new Artist(id, nume);
                        tasksR.Add(artist);
                    }
                }
            }

            return tasksR;
        }

        public void Remove(int id)
        {
            IDbConnection con = DBUtils.getConnection();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "delete from artists where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No artist deleted!");
                log.Info("Artist removed!");
            }
        }

        public void Update(int id, Artist entity)
        {
            IDbConnection con = DBUtils.getConnection();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "update artists set name=@name where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                IDbDataParameter paramNume = comm.CreateParameter();
                paramId.ParameterName = "@name";
                paramId.Value = entity.Name;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No artist updated!");
            }
        }
    }
}
