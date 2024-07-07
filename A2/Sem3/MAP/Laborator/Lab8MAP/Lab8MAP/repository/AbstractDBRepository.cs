using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using Lab8MAP.domain;

namespace Lab8MAP.repository
{
    internal abstract class AbstractDBRepository<ID, E> : IRepository<ID, E> where ID : IComparable<ID> where E : Entity<ID>
    {
        protected const string ConnectionString = "data source=DESKTOP-GAQSC82;initial catalog=MAPL8;trusted_connection=true";
        protected SqlConnection Connection { get; set; }

        protected string tableName, tableFields;
        public string TableName { get { return tableName; } }

        public AbstractDBRepository(string tablename, string tableFields) 
        {
            tableName = tablename.Trim();
            this.tableFields = tableFields.Trim();
            Connection = new SqlConnection(ConnectionString);
        }

        protected SqlDataReader ExecuteReadQuery(string sql)
        {
            SqlCommand commandcmd = new SqlCommand(sql, Connection);
            var reader = commandcmd.ExecuteReader();
            return reader;
        }

        protected void ExecuteUpdateQuery(string sql)
        {
            SqlCommand commandcmd = new SqlCommand(sql, Connection);
            commandcmd.ExecuteNonQuery();
        }

        public E? FindOne(ID id)
        {
            string sql = $"SELECT * FROM {tableName} WHERE id = {id}";
            Connection.Open();
            var reader = ExecuteReadQuery(sql);
            if (reader.Read())
            {
                string[] tokens = new string[reader.FieldCount];
                for (int i = 0; i < reader.FieldCount; ++i)
                    tokens[i] = reader[i].ToString();
                E rez = Parse(tokens);
                Connection.Close();
                return rez;
            }
            return null;
        }

        public ICollection<E> FindAll()
        {
            List<E> list = new List<E>();
            string sql = $"SELECT * FROM {tableName}";
            Connection.Open();
            var reader = ExecuteReadQuery(sql);
            while (reader.Read())
            {
                string[] tokens = new string[reader.FieldCount];
                for (int i = 0; i < reader.FieldCount; ++i)
                    tokens[i] = reader[i].ToString();
                list.Add(Parse(tokens));
            }
            Connection.Close();
            return list;
        }

        public E? Save(E entity)
        {
            string sql = $"INSERT INTO {tableName}({tableFields}) VALUES({GetTableValues(entity)})";
            try
            {
                Connection.Open();
                ExecuteUpdateQuery(sql);
                Connection.Close();
                return entity;
            }
            catch(Exception) {
                try { Connection.Close(); }
                catch(SqlException) { }
                return null; 
            }
        }

        public E? Delete(ID id)
        {
            E? result = FindOne(id);
            if (result == null)
                return null;
            string sql = $"DELETE FROM {tableName} WHERE id = {id}";
            Connection.Open();
            ExecuteUpdateQuery(sql);
            Connection.Close();
            return result;
        }

        protected abstract E Parse(params string[] value);
        protected abstract string GetTableValues(E value);
    }
}
