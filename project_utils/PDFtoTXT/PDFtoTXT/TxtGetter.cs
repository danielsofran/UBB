using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using iText.Kernel.Pdf;
using iText.Kernel.Pdf.Canvas.Parser.Listener;
using iText.Kernel.Pdf.Canvas.Parser;

using Microsoft.Office.Interop.Word;
using Aspose.Pdf.Text;

namespace PDFtoTXT
{
    internal class TxtGetter
    {
        public static void ToTxtAspose(string path)
        {
            int nrpg;
            Aspose.Pdf.Document doc;
            try { doc = new Aspose.Pdf.Document(path); }
            catch (Exception ex) { throw new Exception("Eroare la deschiderea PDF-ului!\nVa rugam sa inchideti pdf-ul!\nCodul erorii: " + ex.ToString()); }
            try
            {
                for (nrpg = 1; nrpg < doc.Pages.Count; ++nrpg)
                {
                    Aspose.Pdf.Document doc0 = new Aspose.Pdf.Document();
                    doc0.Pages.Add(doc.Pages[nrpg]);
                    doc0.Save(String.Format("pdfTemp{0}.pdf", nrpg));

                    Aspose.Words.Document doc1 = new Aspose.Words.Document(String.Format("pdfTemp{0}.pdf", nrpg));
                    doc1.Save(String.Format("txtTemp{0}.txt", nrpg));
                }
            }
            catch (Exception ex) { throw new Exception("Eroare la conversia in TXT!\nCodul erorii: " + ex.ToString()); }
        }

        public static void ToTxtAspose2(string path)
        {
            var document = new Aspose.Pdf.Document(path);

            // create an object of TextDevice
            var renderer = new Aspose.Pdf.Devices.TextDevice();

            renderer.Process(document.Pages[1], "output.txt");
        }

        public static void ToTxtAspose3(string path)
        {
            var document = new Aspose.Pdf.Document(path);
            TextAbsorber textAbsorber = new TextAbsorber();
            String extractedText = textAbsorber.Text;
            document.Pages.Accept(textAbsorber);
            textAbsorber.Visit(document);
            File.WriteAllText("output.txt", extractedText);
        }

        public static string ToTxtIText7(string path)
        {
            string rez = "";
            var src = path;
            var pdfDocument = new PdfDocument(new PdfReader(src));
            
            var strategy = new iText.Kernel.Pdf.Canvas.Parser.Listener.SimpleTextExtractionStrategy();
            for (int i = 1; i <= pdfDocument.GetNumberOfPages(); ++i)
            {
                var page = pdfDocument.GetPage(i);
                string text = PdfTextExtractor.GetTextFromPage(page, strategy);
                rez+=text;
            }
            pdfDocument.Close();
            //File.WriteAllText("output.txt", rez);
            return rez;
        }

        public static void ToTxtCmd(string path)
        {
            string pathout = System.Windows.Forms.Application.StartupPath;
            MessageBox.Show(pathout);
        }

        public static void ToTxtGS(string path)
        {

        }

        public static void ToTxtWord(string path)
        {
            // For complete examples and data files, please go to https://github.com/aspose-pdf/Aspose.PDF-for-.NET
            // Open the source PDF document
            Aspose.Pdf.Document pdfDocument = new Aspose.Pdf.Document(path);

            // Instantiate DocSaveOptions object
            Aspose.Pdf.DocSaveOptions saveOptions = new Aspose.Pdf.DocSaveOptions
            {
                // Specify the output format as DOCX
                Format = Aspose.Pdf.DocSaveOptions.DocFormat.Doc
                // Set other DocSaveOptions params
                // ....
            };
            // Save document in docx format
            string docPath = "output.doc";
            pdfDocument.Save(docPath, saveOptions);

            string folder = System.Windows.Forms.Application.StartupPath;
            string fullpath = folder + docPath;
            Microsoft.Office.Interop.Word.Application application = new Microsoft.Office.Interop.Word.Application();
            Document document = application.Documents.Open(fullpath);
            application.ActiveDocument.SaveAs(folder + "output.xml", WdSaveFormat.wdFormatXMLDocument);
            ((_Application)application).Quit();
        }
    }
}
