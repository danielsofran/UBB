using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MillStrategy.Structure;

namespace MillStrategy.Domain
{
    public class Pozitie : IPozitie
    {
        OctalNumber cod;

        public Pozitie(OctalNumber number = null)
        {
            cod = number;
            if (cod is null) cod = new OctalNumber();
        }

        public OctalNumber Cod => cod;
        public int Square => cod.FirstDigit;
        public bool IsDiagonal => OctalNumber.DigitPredicates.Odd(cod.LastDigit);
        public bool IsLine => OctalNumber.DigitPredicates.Even(cod.LastDigit);

        public new string ToString() => cod;
        public IPozitie FromString(string pozitie) { cod = pozitie; return this; }

        public List<OctalNumber> Vecini
        {
            get
            {
                List<OctalNumber> list = new List<OctalNumber>();
                list.Add(cod.circularSubstract(1));
                list.Add(cod.circularAdd(1));
                if (cod.FirstDigit.IsEven && cod.LastDigit.IsEven)
                {
                    list.Add(cod + 8);
                    list.Add(cod - 8);
                }
                else if (cod.FirstDigit.IsOdd && cod.LastDigit.IsEven)
                {
                    if (cod.FirstDigit == 1)
                    {
                        list.Add(cod + 8);
                    }
                    else if (cod.FirstDigit == 3)
                    {
                        list.Add(cod - 8);
                    }
                }
                return list;
            }
        }
        public bool HasVecin(OctalNumber number) => Vecini.Any((OctalNumber nr) => nr == number);

        public int CompareTo(object obj)
        {
            if (obj is IPozitie)
            {
                IPozitie other = (IPozitie)obj;
                if (this.Cod == other.Cod) return 0;
                if (this.Cod > other.Cod) return 1;
                if (this.Cod < other.Cod) return -1;
            }
            return 0;
        }

        public bool Equals(IPozitie other) => other.Cod == this.Cod;
    }

    public class Pozitii : IPozitii
    {
        public override string ToString()
        {
            string rez = "";
            foreach (var pos in this)
                rez += pos.ToString() + " ";
            return rez;
        }

        public override IPozitii FromString(string pozitii)
        {
            var tokens = pozitii.Split(new char[] { ' ', '\n', '\r' }, StringSplitOptions.RemoveEmptyEntries);
            this.Clear();
            foreach (var token in tokens)
                this.Add(new Pozitie().FromString(token));
            return this;
        }

        public override bool ContainsPozitie(IPozitie pozitie)
        {
            foreach (IPozitie pozitie1 in this)
                if (pozitie1.Cod == pozitie.Cod)
                    return true;
            return false;
        }

        public override bool Equals(IPozitii other)
        {
            foreach (var pos in this)
                if (other.Contains(pos))
                    return true;
            return false;
        }

        public static bool operator==(Pozitii pozities1, Pozitii pozities2)
        {
            foreach (var pos in pozities1)
                if (!pozities2.Any(x => x.Cod == pos.Cod))
                    return false;
            return true;
        }

        public static bool operator !=(Pozitii pozities1, Pozitii pozities2) => !(pozities1 == pozities2);
    }

    public class Structuri : IStructuri
    {
        public override string ToString()
        {
            string rez = "";
            foreach (var pos in this)
                rez += pos.ToString() + ";";
            return rez;
        }
        public override IStructuri FromString(string pozitii)
        {
            var tokens = pozitii.Split(new char[] { ';', '\n', '\r' }, StringSplitOptions.RemoveEmptyEntries);
            this.Clear();
            foreach (var token in tokens)
                this.Add(new Pozitii().FromString(token));
            return this;
        }

        public override bool ContainsPozitii(IPozitii pozitii) => this.Any((IPozitii pozs) => pozs.ToString() == pozitii.ToString());
        public override bool ContainsPozitiiAnyOrder(IPozitii pozitii)
        {
            pozitii.Sort();
            return ContainsPozitii(pozitii);
        }
        public override bool ContainsPozitie(IPozitie pozitie)
        {
            foreach (IPozitii pozities in this)
                if(pozities.ContainsPozitie(pozitie))
                    return true;
            return false;
        }

        public override bool Equals(IStructuri other)
        {
            foreach (var pozitii in this)
                if (other.Contains(pozitii))
                    return true;
            return false;
        }
    }


}
