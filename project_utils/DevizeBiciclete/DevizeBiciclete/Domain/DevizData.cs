using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DevizeBiciclete.Domain
{
    public class DevizData
    {
        uint enters = 0;
        long numar=0;
        ClientData client = new ClientData();
        BicicletaData bicicleta = new BicicletaData();
        ConstatareData constatare = new ConstatareData();
        List<ManoperaData> manopere = new List<ManoperaData>();
        List<PiesaData> piese = new List<PiesaData>();
        float tva = DevizSetari.TVA;

        public static DevizData? Empty = null;

        public uint Enters { get { return enters; } set { enters = value; } }
        public long Numar { get { return numar; } set { numar = value; } }
        public ClientData Client { get { return client; } set { client = value; } }
        public BicicletaData Bicicleta { get { return bicicleta; } set { bicicleta = value; } }
        public ConstatareData Constatare { get { return constatare; } set { constatare = value; } }
        public List<ManoperaData> Manopere { get { return manopere; } set { manopere = value;} }
        public List<PiesaData> Piese { get { return piese; } set { piese = value; } }

        public float TotalPiese { get { return piese.Sum((piesa) => piesa.PretTotal);  } }
        public float TotalManopera { get { return manopere.Sum((manopera) => manopera.PretTotal); } }
        public float TotalFaraTVA { get { return TotalPiese + TotalManopera; } }
        public float TVA { get { return tva; } set { tva = value; } }
        public float TVAdinTotal { get => TotalFaraTVA * TVA; }
        public float TotalCuTVA { get { return TotalFaraTVA + TVAdinTotal; } }

        public override string ToString()
        {
            string sep = "$dev$", mansep="$manoprepo$", piesesep="$pieserepo$";
            string ents = enters.ToString();
            string tvas = tva.ToString();
            string nr = numar.ToString();
            string client = Client.ToString();
            string bicicleta = Bicicleta.ToString();
            string constatare = Constatare.ToString();
            string manopere = "", piese="";
            foreach(var manopera in Manopere)
                manopere+=manopera.ToString()+mansep;
            foreach(var piesa in Piese)
                piese+=piesa.ToString()+piesesep;
            return nr+sep+client+sep+bicicleta+sep+constatare+sep+
                manopere+sep+piese+sep+tvas+sep+ents;
        }

        public static DevizData FromString(string txt)
        {
            if (txt == null || txt == "") throw new Exception("sir vid!");
            string sep = "$dev$", mansep = "$manoprepo$", piesesep = "$pieserepo$";
            string[] tokens = txt.Split(new string[] { sep}, StringSplitOptions.None);
            DevizData deviz = new DevizData();
            deviz.numar = Convert.ToInt64(tokens[0]);
            deviz.client = ClientData.FromString(tokens[1]);
            deviz.bicicleta = BicicletaData.FromString(tokens[2]);
            deviz.constatare = ConstatareData.FromString(tokens[3]);
            string[] tokensman = tokens[4].Split(new string[] { mansep }, StringSplitOptions.None);
            string[] tokenspiese = tokens[5].Split(new string[] { piesesep }, StringSplitOptions.None);
            foreach(var manopera in tokensman)
                if(manopera is not null && manopera != "")
                    deviz.manopere.Add(ManoperaData.FromString(manopera));
            foreach(var piese in tokenspiese)
                if(piese is not null && piese != "")
                    deviz.piese.Add(PiesaData.FromString(piese));
            deviz.tva = float.Parse(tokens[6]);
            deviz.enters = uint.Parse(tokens[7]);
            return deviz;
        }

        public DevizData Clone() => FromString(ToString());

        public static bool operator==(DevizData d1, DevizData d2)
        { 
            if(d1.numar != d2.numar) return false;
            if(d1.bicicleta != d2.bicicleta) return false;
            if(d1.tva != d2.tva) return false;
            if(d1.client != d2.client) return false;
            if(d1.constatare != d2.constatare) return false;
            int lp, lm;
            if((lp=d1.piese.Count) != d2.Piese.Count) return false;
            if((lm=d1.manopere.Count) != d2.manopere.Count) return false;
            for(int i=0; i < lm; i++)
                if(d1.manopere[i] != d2.manopere[i]) return false;
            for(int i=0;i<lp;++i)
                if(d1.piese[i] != d2.piese[i]) return false;
            return true;
        }
        public static bool operator!=(DevizData d1, DevizData d2) => !(d1 == d2);

        #region Classes
        public class ClientData
        {
            string nume = "";
            string adresa = "";
            string telefon = "";
            bool persoanaJuridica;
            string registru = "";
            string ro = "";
            string telfirma = "";
            string denumire = "";

            public string Nume { get { return nume; } set { nume = value; } }
            public string Adresa { get { return adresa; } set { adresa = value; } }
            public bool PersoanaJuridica { get { return persoanaJuridica; } set { persoanaJuridica = value; } }
            public bool PersoanaFizica { get => !persoanaJuridica; set => persoanaJuridica = !value; }
            public string Telefon { get { return telefon; } set { telefon = value; } }
            public string Registru { get { return registru; } set { registru = value; } }
            public string RO { get { return ro; } set { ro = value; } }
            public string TelefonFrima { get => telfirma; set { telfirma = value; } }
            public string Denumire { get { return denumire; } set { denumire = value; } }
            
            public override string ToString()
            {
                string sep = "$cli$";
                return string.Format("{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}", nume, sep, 
                    adresa, sep, telefon, sep, persoanaJuridica.ToString(), sep, registru, sep, ro, sep, telfirma, sep, denumire);
            }
            public static ClientData FromString(string txt)
            {
                string sep = "$cli$";
                string[] tokens = txt.Split(new string[] { sep }, StringSplitOptions.None);
                if (tokens.Length != 8) MessageBox.Show("Citire date client eronata!");
                ClientData rez = new ClientData();
                rez.nume = tokens[0];
                rez.adresa = tokens[1];
                rez.telefon = tokens[2];
                rez.persoanaJuridica = bool.Parse(tokens[3]);
                rez.registru = tokens[4];
                rez.ro = tokens[5];
                rez.telfirma = tokens[6];
                rez.denumire = tokens[7];
                return rez;
            }
            
            public static bool operator==(ClientData c1, ClientData c2)
            {
                return  c1.nume.Egal(c2.nume) && 
                        c1.adresa.Egal(c2.adresa) &&
                        c1.telefon.Egal(c2.telefon) &&
                        c1.persoanaJuridica == c2.persoanaJuridica &&
                        c1.registru.Egal(c2.registru) &&
                        c1.ro.Egal(c2.ro) &&
                        c1.telfirma.Egal(c2.telfirma);
            }
            public static bool operator!=(ClientData c1, ClientData c2) => !(c1 == c2);
        }

        public class BicicletaData
        {
            string marca = "";
            string model = "";
            string culoare = "";
            string serie = "";
            public string Marca { get { return marca; } set { marca = value; } }
            public string Model { get { return model; } set { model = value; } }
            public string Culoare { get { return culoare; } set { culoare = value;} }
            public string Serie { get { return serie; } set { serie = value; } }
            public override string ToString()
            {
                string sep = "$bic$";
                return string.Format("{0}{1}{2}{3}{4}{5}{6}", marca, sep, model, sep, culoare, sep, serie, sep);
            }
            public static BicicletaData FromString(string txt)
            {
                string sep = "$bic$";
                BicicletaData bicicleta = new BicicletaData();
                string[] tokens = txt.Split(new string[] { sep }, StringSplitOptions.None);
                if (tokens.Length != 4) MessageBox.Show("Citire date bicicleta eronata!");
                bicicleta.marca = tokens[0];
                bicicleta.model = tokens[1];
                bicicleta.culoare = tokens[2];
                bicicleta.serie = tokens[3];
                return bicicleta;
            }

            public static bool operator==(BicicletaData b1, BicicletaData b2)
                => b1.marca.Egal(b2.marca) && b1.serie.Egal(b2.serie) && b1.model.Egal(b2.model) && b1.culoare.Egal(b2.culoare);
            public static bool operator !=(BicicletaData b1, BicicletaData b2) => !(b1 == b2);
        }

        public class ConstatareData
        {
            string motiv = "";
            DateTime datain;
            DateTime dataout;
            public string Motiv { get { return motiv; } set { motiv = value; } }
            public DateTime DataIn { get { return datain; } set { datain = value; } }
            public DateTime DataOut { get { return dataout; } set { dataout = value; } }
            public string DataInText { get => string.Format("{0:00}.{1:00}.{2:0000}", datain.Day, datain.Month, datain.Year); }
            public string DataOutText { get => string.Format("{0:00}.{1:00}.{2:0000}", dataout.Day, dataout.Month, dataout.Year); }

            public override string ToString()
            {
                string sep = "$constat$";
                return string.Format("{0}{1}{2}{3}{4}", motiv, sep, datain.Afis(), sep, dataout.Afis());
            }
            public static ConstatareData FromString(string txt)
            {
                string sep = "$constat$";
                string[] tokens = txt.Split(sep, StringSplitOptions.None);
                ConstatareData data = new ConstatareData();
                data.motiv = tokens[0];
                data.datain = Utils.FormatDate(tokens[1]);
                data.dataout = Utils.FormatDate(tokens[2]);
                return data;
            }
            public static bool operator==(ConstatareData c1, ConstatareData c2)
                => c1.motiv.Egal(c2.motiv) && c1.datain.Egal(c2.datain) && c1.dataout.Egal(c2.dataout);
            public static bool operator!=(ConstatareData c1, ConstatareData c2) => !(c1 == c2);
        }

        public class ManoperaData
        {
            string nume="";
            float durata;
            float pret;
            float discount=0;
            public string Nume { get { return nume; } set { nume = value; } }
            public float Durata { get { return durata; } set { durata = value; } }
            public float Pret { get { return pret; } set { pret = value; } }
            public float Discount { get { return discount; } set { discount = value; } }
            public float PretTotal { get { return pret - pret * (discount/100); } }

            public override string ToString()
            {
                string sep = "$manop$";
                return string.Format("{0}{1}{2}{3}{4}{5}{6}", nume, sep, durata.ToString(), sep, pret.ToString(), sep, discount.ToString());
            }
            public static ManoperaData FromString(string txt)
            {
                string sep = "$manop$";
                string[] tokens = txt.Split(new string[] { sep }, StringSplitOptions.None);
                ManoperaData manopera = new ManoperaData();
                manopera.Nume = tokens[0];
                manopera.Durata = float.Parse(tokens[1]);
                manopera.Pret = float.Parse(tokens[2]);
                manopera.Discount = float.Parse(tokens[3]);
                return manopera;
            }

            public static bool operator==(ManoperaData m1, ManoperaData m2)
                => m1.nume.Egal(m2.nume) && m1.durata == m2.durata && m1.pret == m2.pret && m1.discount == m2.discount;
            public static bool operator!=(ManoperaData m1, ManoperaData m2) => !(m1 == m2);
        }

        public  class PiesaData
        {
            string cod="";
            int nrbuc=1;
            string nume="";
            float pret;

            public string Cod { get { return cod; } set { cod = value; } }
            public int NrBuc { get { return nrbuc; } set { nrbuc = value; } }
            public string Nume { get { return nume; } set { nume = value; } }
            public float Pret { get{ return pret; } set { pret = value; } }
            public float PretTotal { get { return pret * nrbuc; } }

            public override string ToString()
            {
                string sep = "$piesa$";
                return string.Format("{0}{1}{2}{3}{4}{5}{6}", cod, sep, nrbuc.ToString(), sep, nume, sep, pret.ToString());
            }
            public static PiesaData FromString(string txt)
            {
                string sep = "$piesa$";
                string[] tokens = txt.Split(new string[] { sep }, StringSplitOptions.None);
                PiesaData piesa = new PiesaData();
                piesa.cod = tokens[0];
                piesa.nrbuc = int.Parse(tokens[1]);
                piesa.nume = tokens[2];
                piesa.pret = float.Parse(tokens[3]);
                return piesa;
            }
            public static bool operator==(PiesaData p1, PiesaData p2)
                => p1.nume.Egal(p2.nume) && p1.cod.Egal(p2.cod) && p1.nrbuc == p2.nrbuc && p1.pret == p2.pret;
            public static bool operator !=(PiesaData p1, PiesaData p2) => !(p1 == p2);
        }
        #endregion
    }
}
