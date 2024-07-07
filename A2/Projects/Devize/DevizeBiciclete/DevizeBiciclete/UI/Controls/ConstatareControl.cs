using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Constatare = DevizeBiciclete.Domain.DevizData.ConstatareData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class ConstatareControl : UserControl
    {
        public ConstatareControl()
        {
            InitializeComponent();
        }
        public bool Valid { get => 
                textBox1.Text.Length > 0 && dateTimePicker1.Value <= dateTimePicker2.Value; }
        public Constatare Constatare
        {
            get
            {
                Constatare constatare = new Constatare();
                constatare.Motiv = textBox1.Text;
                constatare.DataIn = dateTimePicker1.Value;
                constatare.DataOut = dateTimePicker2.Value;
                return constatare;
            }
            set
            {
                textBox1.Text = value.Motiv;
                dateTimePicker1.Value = value.DataIn;
                dateTimePicker2.Value = value.DataOut;
            }
        }

        public void SetDateNow()
        {
            dateTimePicker1.Value = DateTime.Now;
            dateTimePicker2.Value = DateTime.Now;
        }
    }
}
