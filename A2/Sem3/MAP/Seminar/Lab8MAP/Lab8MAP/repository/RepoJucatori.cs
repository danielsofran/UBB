using Lab8MAP.domain;
using Lab8MAP.exceptions;
using Lab8MAP.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.repository
{
    internal class RepoJucatori : AbstractDBRepository<int, Jucator<int>>
    {
        public RepoJucatori() : base("Jucator", "idElev, idEchipa") { }

        protected override string GetTableValues(Jucator<int> value)
            => $"{value.ID}, {value.Echipa.ID}";

        protected override Jucator<int> Parse(params string[] value)
        {
            if (value.Length != 2) throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            try
            {
                RepoEchipe echipe = new RepoEchipe();
                Echipa<int>? echipa = echipe.FindOne(int.Parse(value[1]));
                if (echipa == null)
                    throw new ParsingException($"Could not find Echipa {value[1]} for Jucator");

                RepoElevi elevi = new RepoElevi();
                Elev<int>? elev = elevi.FindOne(int.Parse(value[0]));
                if (echipa == null)
                    throw new ParsingException($"Could not find Elev {value[0]} for Jucator");

                Jucator<int> result = new Jucator<int>(elev.ID, elev.Nume, elev.Scoala, echipa);
                return result;
            }
            catch (FormatException)
            {
                throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            }
        }
    }
}
