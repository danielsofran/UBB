using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DevizeBiciclete.Domain;
using System.Diagnostics;
using DevizeBiciclete.Repo;

namespace DevizeBiciclete.Test
{
    static class TestDomain
    {
        static DevizData.ClientData clientData;
        static DevizData.BicicletaData bicicletaData;
        static DevizData.ConstatareData constatareData;
        static DevizData.ManoperaData manoperaData;
        static DevizData.PiesaData piesaData;
        static DevizData devizData;
        static void testClientData()
        {
            clientData = new DevizData.ClientData();
            clientData.PersoanaJuridica = true;
            clientData.Adresa = "c.r.vivu";
            clientData.Telefon = "0729010188";
            clientData.Nume = "Sofran Daniel";
            Debug.Assert(DevizData.ClientData.FromString(clientData.ToString()) == clientData, "error fromstring tostring");
        }

        static void testBicicletaData()
        {
            bicicletaData = new DevizData.BicicletaData();
            bicicletaData.Serie = "xb";
            bicicletaData.Marca = "cross";
            bicicletaData.Culoare = "verde";
            bicicletaData.Model = "mtb";
            Debug.Assert(DevizData.BicicletaData.FromString(bicicletaData.ToString()) == bicicletaData);
        }

        static void testConstatareData()
        {
            constatareData = new DevizData.ConstatareData();
            constatareData.Motiv = "roata stricata";
            constatareData.DataIn = new DateTime(2022, 02, 22);
            constatareData.DataOut = new DateTime(2022, 03, 05);
            Debug.Assert(DevizData.ConstatareData.FromString(constatareData.ToString()) == constatareData);
        }

        static void testManoperaData()
        {
            manoperaData = new DevizData.ManoperaData();
            manoperaData.Nume = "reparatii";
            manoperaData.Durata = 2.5f;
            manoperaData.Pret = 200f;
            Debug.Assert(DevizData.ManoperaData.FromString(manoperaData.ToString()) == manoperaData);
        }

        static void testPiesaData()
        {
            piesaData = new DevizData.PiesaData();
            piesaData.Nume = "piesa";
            piesaData.Pret = 5050.23f;
            piesaData.Cod = "3454354";
            piesaData.NrBuc = 3;
            Debug.Assert(DevizData.PiesaData.FromString(piesaData.ToString()) == piesaData);
        }

        static void testDeviz()
        {
            testClientData();
            testBicicletaData();
            testConstatareData();
            testManoperaData();
            testPiesaData();

            devizData = new DevizData();
            devizData.Numar = 3;
            devizData.Client = clientData;
            devizData.Bicicleta = bicicletaData;
            devizData.Constatare = constatareData;
            devizData.Manopere.Add(manoperaData);
            devizData.Manopere.Add(manoperaData);
            devizData.Piese.Add(piesaData);
            devizData.Piese.Add(piesaData);
            devizData.Piese.Add(piesaData);
            Debug.Assert(DevizData.FromString(devizData.ToString()) == devizData);
        }

        static void testRepo()
        {
            Repository repo = new Repository();
            repo.Path = "repository.data";
            repo.Add(devizData);          
            testDeviz();
            devizData.Client.Nume = "Alin Nagi";
            repo.Add(devizData);
            testDeviz();
            devizData.Bicicleta.Model = "MOntan bike";
            repo.Add(devizData);
            repo.ToFile();
            Debug.Assert(Repository.FromFile(repo.Path).ToList[0].Bicicleta == repo.ToList[0].Bicicleta);
            //File.Delete(repo.Path);
        }

        public static void Run()
        {
            testDeviz();
            testRepo();
            string pdfpath = Application.StartupPath + "testpdf.pdf";

            if (true)
            {
                //try
                //{
                    //devizData.Piese.Clear();
                    //devizData.Manopere.Clear();
                    //devizData.Client.PersoanaFizica = true;
                    devizData.ToImgs(Application.StartupPath);
                    //Process process = new Process();
                    //process.StartInfo.FileName = "explorer.exe";
                    //process.StartInfo.UseShellExecute = false;
                    //process.StartInfo.RedirectStandardOutput = true;
                    //process.StartInfo.RedirectStandardError = true;
                    //process.StartInfo.Arguments = pdfpath;
                    //process.Start();
                //}
                //catch (Exception ex)
                //{
                    //MessageBox.Show(ex.Message);  
                //}
            }
            else {
                TestForm testForm = new TestForm();
                testForm.devizControl1.Deviz = devizData;
                testForm.piesaListControl1.Piese = devizData.Piese;
                testForm.ShowDialog();
            }
        }
    }
}
