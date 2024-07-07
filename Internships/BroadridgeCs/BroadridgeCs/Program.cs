using BroadridgeCs;
using System;
using System.Numerics;

namespace main
{
    public class Program
    {
        static int Rez(List<int> lst)
        {
            int rez = 0;
            int[] speed = new int[102];
            for (int i = 0; i < lst.Count - 1; i += 2)
            {
                int d = lst[i];
                int t_init = lst[i + 1];

                int steps = 0;
                double v1, v2;
                double t = t_init;
                do
                {
                    ++steps;
                    v1 = d / (double)t * 3.6;
                    v2 = d / (double)t * 3.6 / 2.0;

                    v1 = Math.Min(v1, 101);
                    v2 = Math.Min(v2 + 1, 101);

                    Console.WriteLine($"{v1} {v2}");

                    speed[(int)Math.Round(v2)] += 1;
                    speed[(int)Math.Round(v1)] -= 1;

                    t += t_init;
                } while ((int)Math.Round(v1) > 0 && (int)Math.Round(v2) > 0);
                Console.WriteLine("Steps: "+steps);
            }

            for (int i = 1; i <= 100; ++i)
            {
                Console.Write(i + " : " + speed[i] + "  ");
                speed[i] += speed[i - 1];
                Console.WriteLine(speed[i]);
                if (speed[i] == 0)
                    rez = i;
            }
            return rez;
        }

        //static int Rez(List<int> lst)
        //{
        //    for(int v=100;v>0;--v)
        //    {
        //        bool fail = false;
        //        for(int i=0;i<lst.Count-1;i+=2)
        //        {
        //            int d = lst[i];
        //            int t = lst[i+1];

        //            double ajuns = d / (double)v * 3.6;

        //            if((int)(ajuns / t) % 2 == 1)
        //            {
        //                fail = true;
        //                break;
        //            }
        //        }
        //        if (!fail)
        //            return v;
        //    }
        //    return 0;
        //}

        public static void Main(string[] args)
        {
            //List<int> data = new List<int>() { 100, 10, 200, 10}; // out: 100
            List<int> data = new List<int>() { 100, 10, 200, 15, 300, 10 }; // out: 54
            Console.WriteLine(Rez(data));
            //Threading.main();
        }
    }
}