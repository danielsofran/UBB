using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TemaCS.controller
{
    internal class Utils
    {
        internal static void Execute(Action action) 
        {
            try { action.Invoke(); }
            catch(ApplicationException e) { MessageBox.Show(e.Message); }
        }
    }
}
