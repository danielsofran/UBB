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
    internal class RepoJucatoriActivi : AbstractDBRepository<int, JucatorActiv<int>>
    {
        public RepoJucatoriActivi() : base("JucatorActiv", "idJucator, idMeci, nrPuncteInscrise, tip") { }

        protected override string GetTableValues(JucatorActiv<int> value)
            => $"{value.IdJucator}, {value.IdMeci}, {value.NrPuncteInscrise}, {(int)value.TipJucator}";

        protected override JucatorActiv<int> Parse(params string[] value)
        {
            if (value.Length != 4) throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            try
            {
                JucatorActiv<int> jucatorActiv = new JucatorActiv<int>(int.Parse(value[0]), int.Parse(value[1]), int.Parse(value[2]), (TipJucator)int.Parse(value[3]));
                return jucatorActiv;
            }
            catch (FormatException)
            {
                throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            }
        }
    }
}
