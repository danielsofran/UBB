using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MillStrategy;
using MillStrategy.Domain;

namespace MillStrategy.Tests
{
    internal static class Tests
    {
        static void test_OctalNumber()
        {
            OctalNumber nr = 2;
            Debug.Assert(nr == 2);
            nr += 6;
            Debug.Assert(nr == 8);
            Debug.Assert(nr == "10");
            Debug.Assert(nr.FirstDigit == 1);
            Debug.Assert(nr.LastDigit == 0);
            Debug.Assert(nr.FirstDigit == '1');
            Debug.Assert(nr.LastDigit == '0');
            Debug.Assert(nr.FirstDigit == "1");
            Debug.Assert(nr.LastDigit == "0");
            Debug.Assert(nr[0] == 1);
            Debug.Assert(nr[1] == 0);
            nr.FirstDigit = 2;
            Debug.Assert(nr == "20");
            nr = 0;
            nr.FirstDigit = '2';
            Debug.Assert(nr == "20");
            nr = 1;
            nr.LastDigit = 2;
            Debug.Assert(nr == 2);
            nr = '2';
            Debug.Assert(nr == 2);
            Debug.Assert(OctalNumber.AreConsecutive(7, 8, 9));
            Debug.Assert(OctalNumber.AreConsecutive("10", "11", "12"));
            Debug.Assert(!OctalNumber.AreConsecutive("10", "10", "10"));
            Debug.Assert(OctalNumber.AreConsecutive(OctalNumber.DigitOptions.First, "10", "20", "30"));
            Debug.Assert(OctalNumber.AreConsecutive(OctalNumber.DigitOptions.Last, "10", "21", "32"));
            Debug.Assert(!OctalNumber.AreConsecutive(OctalNumber.DigitOptions.Last, "7", "10", "11"));
            Debug.Assert("123".Substring(0, 0) == "");
            Debug.Assert(nr.circularAdd(2) == 4);
            Debug.Assert(nr.circularAdd(6) == 0);
            nr.FirstDigit = 1;
            nr.LastDigit = '0';
            Debug.Assert(nr.circularAdd(2) == "12");
            Debug.Assert(nr.circularAdd("21") == "11");
            Debug.Assert(nr.circularSubstract("1") == "17");
            Debug.Assert(nr.circularSubstract("10") == "10");
        }

        static void test_pozitie()
        {
            Pozitie poz = (Pozitie)new Pozitie().FromString("17");
            Debug.Assert(poz.Vecini.Count == 2);
            Debug.Assert(poz.HasVecin("16"));
            Debug.Assert(poz.HasVecin("10"));
            poz.FromString("20");
            Debug.Assert(poz.Vecini.Count == 4);
            Debug.Assert(poz.HasVecin("30"));
            Debug.Assert(poz.HasVecin("10"));
            Debug.Assert(poz.HasVecin("27"));
            Debug.Assert(poz.HasVecin("21"));
            poz.FromString("12");
            Debug.Assert(poz.Vecini.Count == 3);
            Debug.Assert(poz.HasVecin("11"));
            Debug.Assert(poz.HasVecin("13"));
            Debug.Assert(poz.HasVecin("22"));
            poz.FromString("30");
            Debug.Assert(poz.Vecini.Count == 3);
        }

        static void test_Tabla()
        {
            Tabla t = new Tabla();
            Debug.Assert(t.Player == t.Player1);
            t.ChangePlayer();
            Debug.Assert(t.Player == t.Player2);
            Debug.Assert(t.Player.State == Structure.PlayerState.Pune);
        }

        static void test_perm()
        {
            var perms = Permutations.GetPermutations(new List<int> { 1, 2, 3, 4, 5 }, 3);
            Debug.Assert(perms.Count() == 5 * 4 * 3);
        }

        static void test_AreCircularConsecutive()
        {
            Debug.Assert(OctalNumber.AreCircularConsecutive
                ("17", "10", "11"));
            Debug.Assert(OctalNumber.AreCircularConsecutive
                ("16", "10", "17"));
        }

        static void test_mori()
        {
            Structuri mori = Generator.MoriValide;
            Pozitii moara = new Pozitii();
            moara.Add(new Pozitie("17"));
            moara.Add(new Pozitie("10"));
            moara.Add(new Pozitie("11"));
            Debug.Assert(mori.ContainsPozitiiAnyOrder(moara));
        }

        static void test_Game()
        {
            // pune piese
            Tabla t = new Tabla();
            for(OctalNumber nr = "10"; nr < "10" + new OctalNumber(16); nr++)
                t.ProcessPosition(new Pozitie(nr));
            t.ProcessPosition(new Pozitie("37")); // alb
            t.ProcessPosition(new Pozitie("33")); // negru

            Debug.Assert(t.Player1.Luate.Count == 0);
            Debug.Assert(t.Player2.Luate.Count == 0);
            Debug.Assert(t.Player1.DePus.Count == 0);
            Debug.Assert(t.Player2.DePus.Count == 0);

            // muta alb jos
            t.ProcessPosition(new Pozitie("37"));
            t.ProcessPosition(new Pozitie("36"));
            // iau piesa 11
            t.ProcessPosition(new Pozitie("11"));
            // mut cu negru sus
            t.ProcessPosition(new Pozitie("33"));
            t.ProcessPosition(new Pozitie("32"));
            // deschid moara
            t.ProcessPosition(new Pozitie("36"));
            t.ProcessPosition(new Pozitie("35"));
            // mut negru inapoi jos
            t.ProcessPosition(new Pozitie("32"));
            t.ProcessPosition(new Pozitie("33"));
            // mut alb inapoi sus
            t.ProcessPosition(new Pozitie("35"));
            t.ProcessPosition(new Pozitie("36"));
            // iau piesa neagra 17
            t.ProcessPosition(new Pozitie("17"));

            Debug.Assert(t.Player.Luate.Count == 2);
        }

        public static void Run()
        {
            test_OctalNumber();
            test_pozitie();
            test_Tabla();
            test_perm();
            test_AreCircularConsecutive();
            test_mori();
            test_Game();
        }
    }
}
