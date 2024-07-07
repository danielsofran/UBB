using Aspose.Pdf;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PDFDirect
{
    public static class Convertor
    {
        public static Document Image(string path)
        {
            // Initialize new PDF document
            System.Drawing.Image srcImage = System.Drawing.Image.FromFile(path);

            // Read Height of input image
            int h = srcImage.Height;

            // Read Height of input image
            int w = srcImage.Width;

            // Initialize a new PDF document
            Document doc = new Document();

            // Add an empty page
            Page page = doc.Pages.Add();
            Aspose.Pdf.Image image = new Aspose.Pdf.Image();
            image.File = (path);

            // Set page dimensions and margins
            page.PageInfo.Height = (h);
            page.PageInfo.Width = (w);
            page.PageInfo.Margin.Bottom = (0);
            page.PageInfo.Margin.Top = (0);
            page.PageInfo.Margin.Right = (0);
            page.PageInfo.Margin.Left = (0);
            page.Paragraphs.Add(image);

            return doc;
        }

        public static Document Jpg(string path)
        {
            Document doc = new Document();

            // Add empty page in empty document
            Page page = doc.Pages.Add();
            Aspose.Pdf.Image image = new Aspose.Pdf.Image();
            image.File = (path);

            // Add image on a page
            page.Paragraphs.Add(image);
            return doc;
        }

        public static Document Png(string path)
        {
            System.Drawing.Image srcImage = System.Drawing.Image.FromFile(path);
            int h = srcImage.Height;
            int w = srcImage.Width;

            // Initialize new Document
            Document doc = new Document();
            Page page = doc.Pages.Add();
            Aspose.Pdf.Image image = new Aspose.Pdf.Image();
            image.File = (path);

            // Set page dimensions
            page.PageInfo.Height = (h);
            page.PageInfo.Width = (w);
            page.PageInfo.Margin.Bottom = (0);
            page.PageInfo.Margin.Top = (0);
            page.PageInfo.Margin.Right = (0);
            page.PageInfo.Margin.Left = (0);
            page.Paragraphs.Add(image);

            return doc;
        }

        public static Document Tiff(string path)
        {
            Document pdf = new Document();

            //Load TIFF image into stream
            MemoryStream ms = new MemoryStream();
            new FileStream(path, FileMode.Open).CopyTo(ms);
            Bitmap myimage = new Bitmap(ms);
            // Convert multi page or multi frame TIFF to PDF
            FrameDimension dimension = new FrameDimension(myimage.FrameDimensionsList[0]);
            int frameCount = myimage.GetFrameCount(dimension);

            // Iterate through each frame
            for (int frameIdx = 0; frameIdx <= frameCount - 1; frameIdx++)
            {
                Page sec = pdf.Pages.Add();

                myimage.SelectActiveFrame(dimension, frameIdx);

                MemoryStream currentImage = new MemoryStream();
                myimage.Save(currentImage, ImageFormat.Tiff);

                Aspose.Pdf.Image imageht = new Aspose.Pdf.Image();
                imageht.ImageStream = currentImage;
                sec.Paragraphs.Add(imageht);
            }
            return pdf;
        }

        public static Document Emf(string path)
        {
            // Initialize new PDF document
            var doc = new Document();

            // Spcify path of input EMF image file
            var imageFile = path;
            var page = doc.Pages.Add();
            string file = imageFile;
            FileStream filestream = new FileStream(file, FileMode.Open, FileAccess.Read);
            BinaryReader reader = new BinaryReader(filestream);
            long numBytes = new FileInfo(file).Length;
            byte[] bytearray = reader.ReadBytes((int)numBytes);
            Stream stream = new MemoryStream(bytearray);
            var b = new Bitmap(stream);

            // Specify page dimesion properties
            page.PageInfo.Margin.Bottom = 0;
            page.PageInfo.Margin.Top = 0;
            page.PageInfo.Margin.Left = 0;
            page.PageInfo.Margin.Right = 0;
            page.PageInfo.Width = b.Width;
            page.PageInfo.Height = b.Height;
            var image = new Aspose.Pdf.Image();
            image.File = imageFile;
            page.Paragraphs.Add(image);

            return doc;
        }

        public static Document Bmp(string path)
        {
            Document pdfDocument = new Document();
            pdfDocument.Pages.Add();
            Aspose.Pdf.Image image = new Aspose.Pdf.Image();

            // Load sample BMP image file
            image.File = path;
            pdfDocument.Pages[1].Paragraphs.Add(image);

            // Save output PDF document
            return pdfDocument;
        }

        static bool IsValidImage(string filename)
        {
            try
            {
                using (System.Drawing.Image newImage = System.Drawing.Image.FromFile(filename))
                { }
            }
            catch (OutOfMemoryException ex)
            {
                //The file does not have a valid image format.
                //-or- GDI+ does not support the pixel format of the file

                return false;
            }
            catch (Exception ex)
            {
                //The file does not have a valid image format.
                //-or- GDI+ does not support the pixel format of the file

                return false;
            }
            return true;
        }

        public static List<Tuple<Document, string>> FromDirectory(string dpath, bool onepdf=false)
        {
            List<Tuple<Document, string>> rez = new List<Tuple<Document, string>>();
            string[] files = Directory.GetFiles(dpath, "*", SearchOption.AllDirectories);
            Document Pdf = new Document();
            Page page = Pdf.Pages.Add();
            foreach (string f in files)
                if(IsValidImage(f))
                {
                    if (onepdf) page.Paragraphs.Add(Image(f).Pages.Last().Paragraphs.Last());
                    else
                    {
                        Document d = Image(f);
                        string name = Path.GetFileName(f).Replace(f.Substring(f.LastIndexOf('.') + 1), "pdf");
                        rez.Add(new Tuple<Document, string>(Image(f), name));
                        //doc.Save();
                    }
                }
            if (onepdf) rez.Add(new Tuple<Document, string>(Pdf, "Pdf.pdf"));
            return rez;
        }
    }
}
