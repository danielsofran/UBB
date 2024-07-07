using Lab8MAP.tests;
using Lab8MAP.ui;

namespace Lab8MAP
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            Test.RunTests();
            ApplicationConfiguration.Initialize();
            Application.Run(new MainFrom());
        }
    }
}