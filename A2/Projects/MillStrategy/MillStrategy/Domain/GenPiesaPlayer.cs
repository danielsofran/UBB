using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Threading.Tasks;
using MillStrategy.Structure;

namespace MillStrategy.Domain
{
    public static class Generator
    {
        public static Pozitii Pozitii
        {
            get
            {
                Pozitii pozitiivalide = new Pozitii();
                for (int i = 8; i <= 31; ++i)
                    pozitiivalide.Add(new Pozitie(i));
                return pozitiivalide;
            }

        }
        public static Structuri MoriValide
        {
            get
            {
                Structuri mori = new Structuri();
                if (LoadMoriFromFile(ref mori)) return mori;

                Pozitii pozitii = Pozitii;
                foreach (var list in Permutations.GetPermutations(pozitii, 3))
                {
                    Pozitii pozs = new Pozitii();
                    foreach (var item in list) pozs.Add(item);
                    pozs.Sort();
                    if (pozs.All((IPozitie poz) => poz.Cod.LastDigit == pozs[0].Cod.LastDigit) && pozs[0].Cod.LastDigit.IsEven && !mori.ContainsPozitii(pozs))
                        mori.Add(pozs);
                    else
                    {
                        var numbers = from poz in pozs select poz.Cod;
                        int odds = numbers.Count(OctalNumber.DigitPredicates.Odd);
                        if (OctalNumber.AreCircularConsecutive(numbers.ToArray()) && odds == 2)
                        {
                            if (!mori.ContainsPozitii(pozs))
                                mori.Add(pozs);
                        }
                    }
                }
                File.WriteAllText("mori.txt", mori.ToString());
                return mori;
            }
        }

        static bool LoadMoriFromFile(ref Structuri mori, string path = "mori.txt")
        {
            mori = new Structuri();
            if (!File.Exists(path)) return false;
            mori.FromString(File.ReadAllText(path));
            return true;
        }
    }

    public class Piesa : IPiesa
    {
        public Piesa(PiesaColor playerColor = PiesaColor.None, PiesaState state = PiesaState.Nepusa)
        {
            PlayerColor = playerColor;
            State = state;
        }

        public PiesaColor PlayerColor { get; set; } = PiesaColor.None;
        public bool IsBlack { get => PlayerColor == PiesaColor.Black; set => PlayerColor = value == true ? PiesaColor.Black : PiesaColor.None; }
        public bool IsWhite { get => PlayerColor == PiesaColor.White; set => PlayerColor = value == true ? PiesaColor.White : PiesaColor.None; }
        public bool Is(PiesaColor color) => PlayerColor == color;
        public bool Ocupata { get => PlayerColor != PiesaColor.None; }
        public bool Libera { get => PlayerColor == PiesaColor.None; set => PlayerColor = PiesaColor.None; }
        public PiesaState State { get; set; }
    }

    public class Player : IPlayer
    {
        public Player(PiesaColor piesaColor = PiesaColor.None, string nume = "Player")
        {
            Nume = nume;
            PiesaColor = piesaColor;
            Reset();
        }

        public string Nume { get; set; } = "Player";
        public PiesaColor PiesaColor { get; set; } = PiesaColor.None;
        public PlayerState State { get; set; } = PlayerState.Pune;
        public List<IPiesa> DePus { get; set; }
        public List<IPiesa> Luate { get; set; }

        public void Reset()
        {
            List<IPiesa> depus = new List<IPiesa>();
            for (int i = 0; i < 9; ++i)
            {
                Piesa p = new Piesa(PiesaColor, PiesaState.Nepusa);
                depus.Add(p);
            }
            DePus = depus;
            Luate = new List<IPiesa>();
        }
        public IPiesa Pune()
        {
            var piesa = DePus.Last();
            DePus.RemoveAt(DePus.Count - 1);
            if (DePus.Count == 0)
                State = PlayerState.MutaStart;
            return piesa;
        }
        public void Ia(IPiesa piesa)
        {
            Luate.Add(piesa);
        }
    }
}
