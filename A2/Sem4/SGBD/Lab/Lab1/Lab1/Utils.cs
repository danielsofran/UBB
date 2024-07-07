using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    internal class Utils
    { 
        internal static Configuration Configuration { get => ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location);}
        internal static string ConnectionString { get => Configuration.ConnectionStrings.ConnectionStrings[0].ConnectionString; }
    }
}
