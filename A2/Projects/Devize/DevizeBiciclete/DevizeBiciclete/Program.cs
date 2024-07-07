using DevizeBiciclete.Test;
using DevizeBiciclete.Domain;
using DevizeBiciclete.UI;

namespace DevizeBiciclete
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
            ApplicationConfiguration.Initialize();
            DevizSetari.Logo.Save(Application.StartupPath+"logo.png");
            if (!File.Exists(Application.StartupPath + "setari.settings"))
                DevizSetari.ToFile(Application.StartupPath + "setari.settings");
            else
            {
                try { DevizSetari.FromFile(Application.StartupPath + "setari.settings"); } catch { }
            }
            //TestDomain.Run();
            Application.Run(new MainForm());
        }
    }
}