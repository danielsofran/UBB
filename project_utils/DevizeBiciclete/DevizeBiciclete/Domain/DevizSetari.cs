using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DevizeBiciclete.Domain
{
    public static class DevizSetari
    {
        static string titlu = "DEVIZ";
        static string pdfPath = "";
        static Bitmap logo = Resource.logo_bistritz;

        public static string Titlu { get => titlu; set => titlu = value; }
        public static string PDFPath { get => pdfPath; set => pdfPath = value; }
        public static Bitmap Logo { get => logo; set => logo = value; }
        public static float TVA = .19f; ////////////////////////////////////////// get from file

        public static void ToFile(string path)
        {
            using(StreamWriter sw = new StreamWriter(path))
            {
                sw.WriteLine(pdfPath);
                sw.WriteLine(TVA.ToString());
            }
        }

        public static void FromFile(string path)
        {
            using(StreamReader sr = new StreamReader(path))
            {
                pdfPath = sr.ReadLine();
                TVA = float.Parse(sr.ReadLine());
            }
        }

        public static class Service
        {
            static string titlu ="Furnizor";
            static string nume = "Bistritz Bike Shop SRL";
            static string adresa = "Bistrita, str. Valer Braniste 12";
            static string email = "email@email.com";
            static string telefon = "0745707007";
            static string registru = "J06/105/2018";
            static string ro = "RO38802818";
            //static string cif = "RO38802818";

            public static string Titlu { get => titlu; set => titlu = value; } 
            public static string Nume { get => nume; set => nume = value; }    
            public static string Adresa { get => adresa; set => adresa = value; }
            public static string Email { get => email; set => email = value; }
            public static string Telefon { get => telefon; set => telefon = value;}
            public static string Registru { get => registru; set => registru = value; }
            public static string RO { get => ro; set => ro = value; }
            //public static string CIF { get => cif; set => cif = value; }

        }

        public static class Certificat
        {
            static string title ="CERTIFICAT DE CALITATE SI GARANTIE";
            static string intro;
            static List<string> linii;
            static string final;

            public static string Title { get => title; set => title = value; }
            public static string Intro { get => intro; set => intro = value; }
            public static List<string> Linii { get => linii; set => linii = value;}
            public static string Final { get => final; set => final = value; }
        }

        public static class SemnaturaService
        {
            static string titlu = "Semnatura Service";
            static string text;
            static Bitmap image;
            public static string Titlu { get => titlu; set => titlu = value;}
            public static Bitmap Image { get => image; set => image = value; }
            public static string Text { get => text; set => text = value; }
        }
    }
}
