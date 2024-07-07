using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.model
{
    [Serializable]
    public class User : Entity<int>
    {
        public string Username { get; set; }
        public string Password { get; set; }

        public User(int id, string username, string password) : base(id) 
        {
            Username = username;
            Password = password;
        }

        public User(string username, string password) : base(-1)
        {
            Username = username;
            Password = password;
        }

        public override string ToString() => $"User {Username}";
    }
}
