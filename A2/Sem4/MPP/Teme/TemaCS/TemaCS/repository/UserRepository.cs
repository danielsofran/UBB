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
    internal class UserRepository : IUserRepository
    {
        IDictionary<String, string> props;
        private static readonly ILog log = LogManager.GetLogger("UserRepository");

        public UserRepository(IDictionary<String, string> props)
        {
            log.Info("UserRepo ctor");
            this.props = props;
        }

        public void Add(User entity)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into users(username, password) values (@username, @password)";
                var paramun = comm.CreateParameter();
                paramun.ParameterName = "@username";
                paramun.Value = entity.Username;
                comm.Parameters.Add(paramun);

                var paramps = comm.CreateParameter();
                paramps.ParameterName = "@password";
                paramps.Value = entity.Password;
                comm.Parameters.Add(paramps);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No user added !");
                log.Info("User added!");
            }
        }

        public void Remove(int id)
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "delete from users where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No user deleted!");
                log.Info("User deleted!");
            }
        }

        public void Update(int id, User entity)
        {
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "update users set username=@username password=@password where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                var paramun = comm.CreateParameter();
                paramun.ParameterName = "@username";
                paramun.Value = entity.Username;
                comm.Parameters.Add(paramun);

                var paramps = comm.CreateParameter();
                paramps.ParameterName = "@password";
                paramps.Value = entity.Password;
                comm.Parameters.Add(paramps);

                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No user updated!");
                log.Info("User updated!");
            }
        }

        public User Find(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from users where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String nume = dataR.GetString(1);
                        String password = dataR.GetString(2);
                        User user = new User(id, nume, password);
                        log.InfoFormat("Exiting findOne with value {0}", user);
                        return user;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public User Find(string username, string password)
        {
            log.InfoFormat("Entering findOne with creds {0} {1}", username, password);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from users where username=@username and password=@password";
                var paramun = comm.CreateParameter();
                paramun.ParameterName = "@username";
                paramun.Value = username;
                comm.Parameters.Add(paramun);

                var paramps = comm.CreateParameter();
                paramps.ParameterName = "@password";
                paramps.Value = password;
                comm.Parameters.Add(paramps);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        User user = new User(id, username, password);
                        log.InfoFormat("Exiting findOne with value {0}", user);
                        return user;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public IEnumerable<User> FindAll()
        {
            IDbConnection con = DBUtils.getConnection(props);
            IList<User> tasksR = new List<User>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from users";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String nume = dataR.GetString(1);
                        String password = dataR.GetString(2);
                        User user = new User(id, nume, password);
                        tasksR.Add(user);
                    }
                }
            }
            log.Info("Found all users!");
            return tasksR;
        }
    }
}
