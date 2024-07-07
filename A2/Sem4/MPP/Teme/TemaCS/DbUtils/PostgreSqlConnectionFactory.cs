using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DbUtils
{
    public class PostgreSqlConnectionFactory : ConnectionFactory
    {
        public override IDbConnection createConnection(IDictionary<string, string> props)
        {
            //String connectionString = "URI=file:/Users/grigo/didactic/MPP/ExempleCurs/2017/database/tasks.db,Version=3";
            String connectionString = props["ConnectionString"];
            Console.WriteLine("PostgreSQL ---Se deschide o conexiune la  ... {0}", connectionString);
            return new Npgsql.NpgsqlConnection(connectionString);
        }
    }
}
