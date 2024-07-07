using Lab8MAP.domain;
using Lab8MAP.exceptions;
using Lab8MAP.repository;
using Lab8MAP.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.service
{
    internal class Service
    {
        private IRepository<int, Echipa<int>> repoEchipe;
        private IRepository<int, Elev<int>> repoElevi;
        private IRepository<int, Jucator<int>> repoJucatori;
        private IRepository<int, Meci<int>> repoMeciuri;
        private IRepository<int, JucatorActiv<int>> repoJucatoriActivi;

        public Service()
        {
            repoEchipe = new RepoEchipe();
            repoElevi = new RepoElevi();
            repoJucatori = new RepoJucatori();
            repoJucatoriActivi = new RepoJucatoriActivi();
            repoMeciuri = new RepoMeci();
        }

        public Echipa<int>[] Echipe { get => repoEchipe.FindAll().OrderBy(e => e.Nume).ToArray(); }
        public Meci<int>[] Meciuri { get => repoMeciuri.FindAll().ToArray(); }
        
        public Jucator<int>[] FindJucatoriiEchipei(Echipa<int> echipa) 
        {
            var jucatori = repoJucatori.FindAll();
            var rez = from j in jucatori
                      where j.Echipa.Equals(echipa)
                      select j;
            return rez.ToArray();
        }

        public Jucator<int>[] FindJucatoriiActiviLaMeci(Echipa<int> echipa, Meci<int> meci)
        {
            var jucatori = repoJucatori.FindAll();
            var jucatoriActivi = repoJucatoriActivi.FindAll();
            var rez = from jucatorActiv in jucatoriActivi
                      where jucatorActiv.IdMeci == meci.ID
                      //where jucatorActiv.TipJucator == TipJucator.Participant
                      join jucator in jucatori on jucatorActiv.IdJucator equals jucator.ID
                      where jucator.Echipa.Equals(echipa)
                      select jucator;
            return rez.ToArray();
        }

        public string[] FindJucatoriiActiviLaMeciSiPuncteleInscrise(Echipa<int> echipa, Meci<int> meci)
        {
            var jucatori = repoJucatori.FindAll();
            var jucatoriActivi = repoJucatoriActivi.FindAll();
            var rez = from jucatorActiv in jucatoriActivi
                      where jucatorActiv.IdMeci == meci.ID
                      //where jucatorActiv.TipJucator == TipJucator.Participant
                      join jucator in jucatori on jucatorActiv.IdJucator equals jucator.ID
                      where jucator.Echipa.Equals(echipa)
                      select $"{jucator.Nume} - Puncte Inscrise: {jucatorActiv.NrPuncteInscrise}, {jucatorActiv.TipJucator}";
            return rez.ToArray();
        }

        public Meci<int>[] FindMeciuriByDates(DateTime start, DateTime end)
        {
            if (start > end)
                throw new ServiceException("Datele nu sunt cronologice!");
            var meciuri = repoMeciuri.FindAll();
            var rez = from meci in meciuri
                      where meci.Data.IsBetween(start, end)
                      select meci;
            return rez.ToArray();
        }

        public string FindScor(Meci<int> meci)
        {
            var jucatori = repoJucatori.FindAll();
            var jucatoriActivi = repoJucatoriActivi.FindAll();
            var puncteEchipa1 = from jucatorActiv in jucatoriActivi
                                where jucatorActiv.IdMeci == meci.ID
                                join jucator in jucatori on jucatorActiv.IdJucator equals jucator.ID
                                where jucator.Echipa.Equals(meci.Echipa1)
                                select jucatorActiv.NrPuncteInscrise;
            var puncteEchipa2 = from jucatorActiv in jucatoriActivi
                                where jucatorActiv.IdMeci == meci.ID
                                join jucator in jucatori on jucatorActiv.IdJucator equals jucator.ID
                                where jucator.Echipa.Equals(meci.Echipa2)
                                select jucatorActiv.NrPuncteInscrise;
            return $"{puncteEchipa1.Sum()} - {puncteEchipa2.Sum()}";
        }
    }
}
