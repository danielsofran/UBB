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
    internal class RepoEchipe : AbstractDBRepository<int, Echipa<int>>
    {
        public RepoEchipe() : base("Echipe", "id, nume") { }

        protected override string GetTableValues(Echipa<int> value)
            => $"{value.ID}, '{value.Nume}'";

        protected override Echipa<int> Parse(params string[] value)
        {
            if (value.Length != 2) throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            try
            {
                Echipa<int> result = new Echipa<int>(int.Parse(value[0]), value[1]);
                return result;
            }
            catch (FormatException)
            {
                throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            }
        }
    }
}
