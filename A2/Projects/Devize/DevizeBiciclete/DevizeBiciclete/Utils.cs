using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DevizeBiciclete
{
    public static class Utils
    {
        public static bool Egal(this string s1, string s2)
        {
            //if (s1 is null) return true;
            //if (s2 is null) return true;
            return s1.Equals(s2);
        }

        public static bool Egal(this DateTime d1, DateTime d2)
        {
            return d1.Day == d2.Day && d1.Month == d2.Month && d1.Year == d2.Year;
        }

        //public static bool Egal<T>(this List<T> lista1, List<T> lista2)
        //{
        //    int l;
        //    if ((l=lista1.Count) != lista2.Count) return false;
        //    for(int i=0;i<l;++i)
        //        if(lista1[i] != lista2[i]) 
        //            return false;
        //    return true;
        //}

        public static string Afis(this DateTime d) => string.Format("{0}.{1}.{2}", d.Year, d.Month, d.Day);
        public static DateTime FormatDate(string txt)
        {
            if (txt == null) return DateTime.MinValue;
            string[] tokens = txt.Split('.');
            DateTime dt = new DateTime(int.Parse(tokens[0]), int.Parse(tokens[1]), int.Parse(tokens[2]));
            return dt;
        }
        public static bool AreDiacritice(this string str)
        {
            string diacritice = "ăîâșț";
            foreach(char ch in diacritice)
                if(str.Contains(ch))
                    return true;
            return false;
        }

        public static string FaraDiacritice(this string str)
        {
            string rez = str;
            rez = rez.Replace('ă', 'a');
            rez = rez.Replace('â', 'a');
            rez = rez.Replace('î', 'i');
            rez = rez.Replace('ț', 't');
            rez = rez.Replace('ș', 's');
            rez = rez.Replace('Ă', 'A');
            rez = rez.Replace('Â', 'A');
            rez = rez.Replace('Î', 'I');
            rez = rez.Replace('Ș', 'S');
            rez = rez.Replace('Ț', 'T');
            return rez;
        }
    }
}
