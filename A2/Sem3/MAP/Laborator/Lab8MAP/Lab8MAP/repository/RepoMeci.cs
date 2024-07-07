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
    internal class RepoMeci : AbstractDBRepository<int, Meci<int>>
    {
        public RepoMeci() : base("Meci", "id, idEchipa1, idEchipa2, [data]") { }

        protected override string GetTableValues(Meci<int> value)
            => $"{value.ID}, {value.Echipa1.ID}, {value.Echipa2.ID}, '{value.Data}'";

        protected override Meci<int> Parse(params string[] value)
        {
            if (value.Length != 4) throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            try
            {
                RepoEchipe echipe = new RepoEchipe();
                Echipa<int>? echipa1 = echipe.FindOne(int.Parse(value[1]));
                Echipa<int>? echipa2 = echipe.FindOne(int.Parse(value[2]));
                if (echipa1 == null || echipa2 == null)
                    throw new ParsingException($"O echipa este vida la parsarea meciului {value[0]}!");
                Meci<int> result = new Meci<int>(int.Parse(value[0]), echipa1, echipa2, DateTime.Parse(value[3]));
                return result;
            }
            catch (FormatException)
            {
                throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            }
        }
    }
}
