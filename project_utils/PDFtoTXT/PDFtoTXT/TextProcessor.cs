using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Pair = System.Collections.Generic.KeyValuePair;


namespace PDFtoTXT
{
    public static class TextProcessor
    {
        static KeyValuePair<int, int> KVINULL = new KeyValuePair<int, int>(-1, -1);
        static KeyValuePair<int, int> GetBetween(this string txt, string strstart, string strend, string strafter = "", bool skipdotsverif=false)
        {
            int indexstart = 0;
            //if (strafter != "") indexstart = txt.IndexOf(strafter);
            indexstart = Math.Max(0, indexstart);
            int ms = txt.IndexOf(strstart, indexstart);
            int me = txt.IndexOf(strend, ms+1);
            if(strstart.Contains(strend)) me = txt.IndexOf(strend, me+1);
            if (ms == -1 || me == -1) return KVINULL;
            init:
            if (!skipdotsverif && txt.SubstringSE(ms, me).Count(ch => (ch == ':')) != 1)
            {
                ms += strstart.Length;
                goto init;
            }
            if (ms == -1 || me == -1) return KVINULL;
            ms += strstart.Length;
            while (char.IsWhiteSpace(txt[ms])) ++ms;
            while (char.IsWhiteSpace(txt[me])) --me;
            return new KeyValuePair<int, int>(ms, me);
        }
        static string SubstringSE(this string s, int start, int end) => s.Substring(start, end - start);
        static string divPow10(string txt, int putere = 6)
        {
            decimal val = Convert.ToDecimal(txt);
            decimal dif = val - Math.Truncate(val);
            if (txt.Contains('.') && Math.Abs(dif) < (decimal)Math.Pow(10, -6))
            {
                txt = txt.Replace(".", ",");
                val = Convert.ToDecimal(txt);
            }
            val /= (decimal)Math.Pow(10, putere);
            return val.ToString().Replace(",", ".");
        }
        public static List<string> GetNumbers(string txt, Action<string> act = null)
        {
            if (act == null) act = REAL;
            string[] tokens = txt.Split(' ');
            List<string> rezs = new List<string>();
            foreach (string token in tokens)
            {
                try { act.Invoke(token); rezs.Add(token); }
                catch (Exception ex) { continue; }
            }
            return rezs;
        }
        static Action<string> REAL = (token) => decimal.Parse(token);
        static Action<string> NATURAL = (token) => int.Parse(token);

        public static List<string> GetFiles(string path_init)
        {
            List<string> paths = new List<string>();
            if (File.Exists(path_init) && path_init.EndsWith(".pdf")) paths.Add(path_init);
            else if(Directory.Exists(path_init)) 
                paths = Directory.GetFiles(path_init, "*.pdf", SearchOption.AllDirectories).Select(x => Path.GetFullPath(x)).ToList();
            return paths;
        }

        class OldDataTxt
        {

            public static List<string> GetNumeProgram(string txt)
            {
                txt = new string(txt);
                int noid, geo;
                string last_noid = "";
                string start_str = "NOID";
                List<string> rez = new List<string>();
                do
                {
                    noid = txt.IndexOf(start_str);
                    if (noid == -1) break;
                    if (last_noid != "" && txt.Substring(noid, last_noid.Length) == last_noid)
                    {
                        txt = txt.Substring(noid + start_str.Length);
                        continue;
                    }
                    int nows = start_str.Length; while (!char.IsWhiteSpace(txt[noid + nows])) nows++;
                    last_noid = txt.Substring(noid, nows);
                    geo = txt.IndexOf("GEO", noid + start_str.Length);
                    string pprogr = txt.SubstringSE(noid, geo);
                    int lastp = pprogr.LastIndexOf('.');
                    int lastbs = pprogr.LastIndexOf('\\') + 1;
                    while (pprogr[lastbs] == ' ') lastbs++;
                    string progrname = pprogr.SubstringSE(lastbs, lastp);
                    rez.Add(progrname);
                    txt = txt.Substring(geo + 1);
                }
                while (noid != -1);
                return rez;
            }

            public static string GetMaterial(string txt)
            {
                string strstart = "MATERIAL (SHEET):";
                string strend = "MATERIAL (TT):";
                var mse = txt.GetBetween(strstart, strend);
                if (mse.Equals(KVINULL))
                {
                    strstart = "MATERIAL ID (SHEET):";
                    strend = "MATERIAL (TT):";
                    mse = txt.GetBetween(strstart, strend);
                    if (mse.Equals(KVINULL))
                    {
                        MessageBox.Show("Eroare la material! Nu exista intre string-urile specificate!");
                        return "";
                    }
                }
                int ms = mse.Key;
                int me = mse.Value;
                int pminus = txt.IndexOf('-', ms);
                string rez = txt.SubstringSE(ms, pminus);
                return rez;
            }

            public static List<KeyValuePair<string, string>> GetDimensions(string txt)
            {
                var rez = new List<KeyValuePair<string, string>>();
                string strstart = "DIMENSIONS:";
                string strend = "SURFACE:";
                txt = new string(txt);
                do
                {
                    var mse = txt.GetBetween(strstart, strend);
                    if (mse.Equals(KVINULL)) break;
                    int ms = mse.Key;
                    int me = mse.Value;
                    string dim = txt.SubstringSE(ms, me);
                    var rezs = GetNumbers(dim);
                    if (rezs.Count != 2)
                    {
                        MessageBox.Show("Eroare la dimensiune, nu sunt exact 2 nr reale in sirul cautat!");
                        continue;
                    }
                    rez.Add(new KeyValuePair<string, string>(rezs[0], rezs[1]));
                    txt = txt.Substring(me + 1);
                } while (true);
                return rez;
            }

            public static string GetGrosime(string txt)
            {
                string strstart = "BLANK:";
                string strend = "MINIMUM BLANK:";
                var mse = txt.GetBetween(strstart, strend);
                if (mse.Equals(KVINULL))
                {
                    MessageBox.Show("Eroare la material! Nu exista intre string-urile specificate!");
                    return "";
                }
                int ms = mse.Key;
                int me = mse.Value;
                var nrs = GetNumbers(txt.SubstringSE(ms, me));
                if (nrs.Count != 3)
                {
                    MessageBox.Show("Grosimea nu a fost gasita intre " + strstart + " si " + strend);
                    return "";
                }
                return nrs[2];
            }

            public static List<string> GetMachineTime(string txt)
            {
                List<string> rez = new List<string>();
                string strstart = "MACHINING TIME:";
                string strend = "CUTTING LENGTH:";
                string strafter = "NAME OF DRAWING:";
                txt = new string(txt);
                do
                {
                    var mse = txt.GetBetween(strstart, strend, strafter);
                    if (mse.Equals(KVINULL)) break;
                    int ms = mse.Key;
                    int me = mse.Value;
                    string mt = txt.SubstringSE(ms, me);
                    var rezs = GetNumbers(mt);
                    if (rezs.Count != 1)
                    {
                        MessageBox.Show("Eroare la Machine Time, nu este exact 1 nr real in sirul cautat!");
                        continue;
                    }
                    rez.Add(rezs.First());
                    txt = txt.Substring(me + 1);
                } while (true);
                return rez;
            }

            public static List<string> GetGreutati(string txt)
            {
                List<string> rez = new List<string>();
                string strstart = "WEIGHT:";
                string strend = "NUMBER OF PIERCING POINTS:";
                string strafter = "NAME OF DRAWING:";
                txt = new string(txt);
                do
                {
                    var mse = txt.GetBetween(strstart, strend, strafter);
                    if (mse.Equals(KVINULL)) break;
                    int ms = mse.Key;
                    int me = mse.Value;
                    string mt = txt.SubstringSE(ms, me);
                    var rezs = GetNumbers(mt);
                    if (rezs.Count != 1)
                    {
                        MessageBox.Show("Eroare la Greutate, nu este exact 1 nr real in sirul cautat!");
                        continue;
                    }
                    rez.Add(rezs.First());
                    txt = txt.Substring(me + 1);
                } while (true);
                return rez;
            }

            public static List<string> GetSuprafete(string txt)
            {
                List<string> rez = new List<string>();
                string strstart = "SURFACE:";
                string strend = "RULE NAME:";
                string strafter = "NAME OF DRAWING:";
                txt = new string(txt);
                do
                {
                    var mse = txt.GetBetween(strstart, strend, strafter);
                    if (mse.Equals(KVINULL)) break;
                    int ms = mse.Key;
                    int me = mse.Value;
                    string mt = txt.SubstringSE(ms, me);
                    var rezs = GetNumbers(mt);
                    if (rezs.Count != 1)
                    {
                        MessageBox.Show("Eroare la Greutate, nu este exact 1 nr real in sirul cautat!");
                        continue;
                    }
                    rez.Add(divPow10(rezs.First()));
                    txt = txt.Substring(me + 1);
                } while (true);
                return rez;
            }

            public override string ToString()
            {
                return base.ToString();
            }
        }

        class NewDataTxt : Data
        {
            static Dictionary<int, string> GetParts(string txt)
            {
                txt = new string(txt);
                var rez = new Dictionary<int, string>();
                string strstart = "PART NUMBER:";
                string strend = ". .";
                string strdbg;
                int index = 0;
                while(index != -1 && index < txt.Length)
                {
                    index = txt.IndexOf(strstart, index) + strstart.Length;
                    strdbg = txt.Substring(index, 10);
                    int indexstart = index;
                    try { while (char.IsWhiteSpace(txt[indexstart++])); }
                    catch (Exception) { index = txt.IndexOf(strstart, index); continue;  }
                    indexstart--;
                    int indexstop = indexstart;
                    try { while (!char.IsWhiteSpace(txt[indexstop++])) ; }
                    catch (Exception) { index = txt.IndexOf(strstart, index); continue; }
                    string str = txt.SubstringSE(indexstart, indexstop);
                    int nr;
                    try { nr = int.Parse(str); }
                    catch (Exception) { index = indexstop; continue; }
                    int dots = txt.IndexOf(". .", indexstop);
                    rez[nr] = txt.SubstringSE(indexstop, dots);
                    //MessageBox.Show(nr.ToString());
                    index = txt.IndexOf(strstart, indexstop);
                }
                return rez;
            }

            static string GetProgram(string txt)
            {
                txt = new string(txt);
                string strstart = "GEOFILE NAME:";
                string strend = ".GEO";
                //MessageBox.Show(txt.Contains(strstart).ToString());
                //var mse = txt.GetBetween(strstart, strend, "GEO", true);
                //if (mse.Equals(KVINULL))
                //{
                //    MessageBox.Show("Eroare la program! Nu exista intre string-urile specificate!");
                //    return "";
                //}
                int ms = txt.LastIndexOf('\\') + 1;
                int me = txt.IndexOf(".GEO");
                string strdbg = txt.Substring(ms, 10);
                while (char.IsWhiteSpace(txt[ms++]));
                string rez;
                try { rez = txt.SubstringSE(ms, me); }
                catch (Exception) { return ""; }
                return rez;
            }

            public static NewDataTxt FromTxt(string txt)
            {
                NewDataTxt data = new NewDataTxt();
                data.Parts = new List<DataPart>();
                var parts = GetParts(txt);
                var material = OldDataTxt.GetMaterial(txt);
                var grosime = OldDataTxt.GetGrosime(txt);
                data.Material = material;
                data.Grosime = grosime;
                foreach (KeyValuePair<int, string> pair in parts)
                {
                    var progrs = GetProgram(pair.Value);
                    var dims = OldDataTxt.GetDimensions(pair.Value);
                    var mtimes = OldDataTxt.GetMachineTime(pair.Value);
                    var greutati = OldDataTxt.GetGreutati(pair.Value);
                    var suprafete = OldDataTxt.GetSuprafete(pair.Value);
                    data.Parts.Add(new DataPart { Program = progrs, Dimensiune = dims.First(), Greutate = greutati.First(), Suprafata = suprafete.First(), MachineTime=mtimes.First() });
                    //MessageBox.Show(string.Format("Part Nr: {0}\n{1}", pair.Key, pair.Value));
                }
                return data;
            }

            public override string ToString()
            {
                string rez = "";
                foreach(var part in this.Parts)
                {
                    rez += part.Program + "\t";
                    rez += "\t";
                    rez += Material + "\t";
                    rez += part.Dimensiune.Key + "\t";
                    rez += part.Dimensiune.Value + "\t";
                    rez += Grosime + "\t";
                    rez += part.MachineTime + "\t";
                    rez += part.Greutate + "\t";
                    rez += part.Suprafata + Environment.NewLine;
                }
                return rez;
            }
        }

        public static string PdfToTxt(string path_init = "test_multiple.pdf", bool skipconversion = true)
        {
            //Aspose.Pdf.Document document = new Aspose.Pdf.Document("test.pdf");
            //Aspose.Pdf.Devices.TextDevice renderer = new Aspose.Pdf.Devices.TextDevice();
            //renderer.Process(document.Pages[1], "outputTemp.txt");
            List<string> paths = GetFiles(path_init);
            string rez = "";
            foreach (string path in paths)
            {
                int nrpg;
                string txt = "";
                if (!skipconversion)
                {
                    txt = TxtGetter.ToTxtIText7(path);
                }
                //txt = File.ReadAllText("output.txt");
                NewDataTxt data = NewDataTxt.FromTxt(txt);
                rez += data.ToString();
            }
            return rez; 
        }

    }
}
