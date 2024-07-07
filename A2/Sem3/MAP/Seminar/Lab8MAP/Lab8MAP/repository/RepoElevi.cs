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
    internal class RepoElevi : AbstractDBRepository<int, Elev<int>>
    {
        public RepoElevi() : base("Elev", "id, nume, scoala") { }

        protected override string GetTableValues(Elev<int> value)
            => $"{value.ID}, '{value.Nume}', '{value.Scoala}'";

        protected override Elev<int> Parse(params string[] value)
        {
            if (value.Length != 3) throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            try
            {
                Elev<int> result = new Elev<int>(int.Parse(value[0]), value[1], value[2]);
                return result;
            }
            catch (FormatException)
            {
                throw new ParsingException($"{tableName} parse ex: " + value.Merge());
            }
        }
    }
}
