using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.persistence
{
    public static class DBUtils
    {


        private static IDbConnection instance = null;


        public static IDbConnection getConnection()
        {
            if (instance == null || instance.State == System.Data.ConnectionState.Closed)
            {
                instance = getNewConnection();
                instance.Open();
            }
            return instance;
        }

        private static IDbConnection getNewConnection()
        {

            String connectionString = "Server=127.0.0.1;Port=5432;Database=MPP_CS;User Id=postgres;Password=parola;";
            return new Npgsql.NpgsqlConnection(connectionString);
        }
    }
}
