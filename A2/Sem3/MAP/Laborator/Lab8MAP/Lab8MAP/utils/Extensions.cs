using Lab8MAP.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.utils
{
    public static class Extensions
    {
        public static string Merge(this string[] strings, string sep = " ")
        {
            return strings.Aggregate((s1, s2) => s1 + sep + s2);
        }

        internal static string GetCode(this TipJucator tip) => ((int)tip).ToString();

        public static bool IsBetween(this DateTime date, DateTime start, DateTime end)
        {
            return date >= start && date <= end;
        }
    }
}
