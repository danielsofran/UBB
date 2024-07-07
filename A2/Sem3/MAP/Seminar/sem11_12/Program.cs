
using Sem11_12.Model;
using Sem11_12.Model.Validator;
using Sem11_12.Repository;
using Sem11_12.Service;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Security.Cryptography;

namespace Sem11_12
{
    class Program
    {

        static void Main(string[] args)
        {
            ConsoleMain(args);
        }

        static void ConsoleMain(string[] args)
        {
            List<Angajat> angajati = GetAngajatService().FindAllAngajati();
            List<Sarcina> sarcini = GetSarcinaService().FindAllSarcini();
            List<Pontaj> pontaje = GetPontajService().FindAllPontaje();

            List<int> l = new List<int>() { 1,2,3,4,5,6,7,8,9,10};          
            var res=l.Where(x => x % 2 == 0);
       
            res.ToList().ForEach(Console.WriteLine);

            DateTime date = DateTime.Now;
            Console.WriteLine(date.ToString("dd/mm/yy hh:MM"));



            // 1. afisati doar angajatii care au nivelul junior - extension methods "Where"
            Console.WriteLine("Cerinta 1");
            var juniors = angajati.Where(angajat => angajat.Nivel == KnowledgeLevel.Junior).ToList();
            juniors.ForEach(x => Console.WriteLine(x));
            Console.WriteLine();

            //2  - cerinta 1 din pdf sem11-12  extension methods 
            Console.WriteLine("Cerinta 2");
            var angSorted = angajati.OrderBy(angajat => angajat.Nivel).ThenByDescending(angajat => angajat.VenitPeOra).ToList();
            angSorted.ForEach(Console.WriteLine);
            Console.WriteLine();

            //2  - cerinta 1 din pdf sem11-12  sql like 
            Console.WriteLine("Cerinta 3");
            var angSorted2 = from angajat in angajati
                            orderby angajat.Nivel, angajat.VenitPeOra descending
                            select angajat;
            angSorted2.ToList().ForEach(Console.WriteLine);
            Console.WriteLine();

            //2 cate ore dureaza in medie fiecare tip de sarcina
            Console.WriteLine("Cerinta 4");
            var sarciniOreMedii = from sarcina in sarcini
                                  group sarcina by sarcina.TipDificultate
                                  into sarcinaMedie
                                  select new { 
                                      dificultate = sarcinaMedie.Key, 
                                      oreMedii = sarcinaMedie.Average(x => x.NrOreEstimate) 
                                  };
            
          Console.WriteLine();

            Console.WriteLine("Cerinta 4");
            var sarciniOreMedii2 = sarcini.GroupBy(X => X.TipDificultate)
                .Select(x => new { dificultate = x.Key, oreMedii = x.Average(y => y.NrOreEstimate) })
                .ToList();
            sarciniOreMedii2.ForEach(Console.WriteLine);
            Console.WriteLine();

            //3 primii doi cei mai harnici angajati
            Console.WriteLine("Cerinta 5");
            var angajatiHarnici = angajati.OrderBy(x => pontaje.Where(y => y.Angajat.ID.Equals(x.ID))
                                          .Sum(y => y.Sarcina.NrOreEstimate * y.Angajat.VenitPeOra))
                                          .ToList();
            angajatiHarnici.ForEach(x => {
                double s = pontaje.Where(y => y.Angajat.ID.Equals(x.ID))
                        .Sum(y => y.Sarcina.NrOreEstimate * x.VenitPeOra);
                Console.WriteLine(x + " " + s);
            });
            angajatiHarnici.ForEach(Console.WriteLine);
            Console.WriteLine();

        }

        private static void Task2()
        {
            

        }


        private static AngajatService GetAngajatService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["angajatiFileName"];
            string fileName = "..\\..\\..\\data\\angajati.txt";
            IValidator<Angajat> vali = new AngajatValidator();

            IRepository<string, Angajat> repo1 = new AngajatInFileRepository(vali, fileName);
            AngajatService service = new AngajatService(repo1);
            return service;
        }

        private static SarcinaService GetSarcinaService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["sarciniFileName"];
            string fileName2 = "..\\..\\..\\data\\sarcini.txt";
            IValidator<Sarcina> vali = new SarcinaValidator();

            IRepository<string, Sarcina> repo1 = new SarcinaInFileRepository(vali, fileName2);
            SarcinaService service = new SarcinaService(repo1);
            return service;
        }

        private static PontajService GetPontajService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["pontajeFileName"];
            string fileName2 = "..\\..\\..\\data\\pontaje.txt";
            IValidator<Pontaj> vali = new PontajValidator();

            IRepository<string, Pontaj> repo1 = new PontajInFileRepository(vali, fileName2);
            PontajService service = new PontajService(repo1);
            return service;
        }

    }
}
