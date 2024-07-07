using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.domain
{
    internal class User : Entity<int>
    {
        public string Username { get; set; }
        public string Password { get; set; }

        public User(int id, string username, string password) : base(id) 
        {
            Username = username;
            Password = password;
        }

        public override string ToString() => $"User {Username}";
    }
}
