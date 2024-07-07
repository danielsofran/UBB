using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Activities;
using System.ComponentModel;
using System.Xml;

namespace ClassLibrary
{
    public class GetGrupe : CodeActivity
    {
        [Category("Input")]
        public InArgument<String> Text { get; set; }

        [Category("Output")]
        public OutArgument<String[]> Grupe { get; set; }

        protected override void Execute(CodeActivityContext context)
        {
            string text = Text.Get(context);
            List<String> list = new List<String>();

            foreach (string row in text.Split(new char[] { '\n' }, StringSplitOptions.RemoveEmptyEntries))
                if (row.Contains("Grupa"))
                    list.Add(row.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Last());

            Grupe.Set(context, list.ToArray());
        }
    }
}
