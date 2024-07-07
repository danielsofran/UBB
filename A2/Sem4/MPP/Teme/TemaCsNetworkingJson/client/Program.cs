using bilete.network.server;
using bilete.services;
using client.controller;
using networking;

namespace bilete.client
{
    public static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();

            IBileteServices server = new BileteServerProxy("127.0.0.1", Ports.Server);
            BileteMainController controller = new BileteMainController(server);
            Console.WriteLine($"Client server started on port {Ports.Server}");
            Application.Run(new LoginForm(controller));
        }
    }
}