using System.Diagnostics;

namespace PDFtoTXT
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
            Application.Run(new Form1());
            //string txt = TextProcessor.PdfToTxt();
            //System.Windows.Forms.Clipboard.SetText(txt);
        }
    }
}