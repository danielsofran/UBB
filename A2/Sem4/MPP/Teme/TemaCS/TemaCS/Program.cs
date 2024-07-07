using log4net.Config;
using Npgsql;
using System.Configuration;
using System.Data;
using System.Reflection;
using TemaCS.controller;
using TemaCS.repository;
using TemaCS.service;

namespace TemaCS
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            //Uri uri = new Uri(Application.StartupPath + "\\log4j.xml");
            //XmlConfigurator.Configure(uri);

            Console.WriteLine("Configuration Settings for tasksDB {0}", GetConnectionStringByName("tasksDB"));
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("showsDB"));

            IUserRepository userRepository = new UserRepository(props);
            IShowRepository showRepository = new ShowRepository(props);
            ITicketRepository ticketRepository = new TicketRepository(props);

            Service service = new Service() { 
                UserRepository = userRepository, 
                ShowRepository = showRepository,
                TicketRepository = ticketRepository
            };

            LoginForm loginForm = new LoginForm();
            loginForm.Service = service;

            Initialize();
            Application.Run(loginForm);
        }

        static void Initialize()
        {
            Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);
            //Control.UseCompatibleTextRenderingDefault = false;
            Application.SetHighDpiMode(HighDpiMode.SystemAware);
        }

        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            var appConfig = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location);
            ConnectionStringSettings settings = appConfig.ConnectionStrings.ConnectionStrings[0];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }
}