using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BroadridgeCs
{
    internal class Threading
    {
        static Func<int> function = () => 3;
        private static async Task f(string sir)
        {
            Console.WriteLine("task began");
            //Thread.Sleep(2000);
            //return 0;
        }
        public static async void main()
        {
            //await f("abc");
            //Console.WriteLine();

            //IEnumerable<int> lst = test_yield();
            //List<int> ints= lst.ToList();
            //Console.WriteLine(ints.Count + " " + ints[0]);

            
        }

        public static IEnumerable<int> test_yield()
        {
            for (int i = 0; i < 10; ++i)
                yield return 3*i+5;
        }
    }
}
