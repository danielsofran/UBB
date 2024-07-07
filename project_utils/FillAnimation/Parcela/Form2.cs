using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Parcela
{
    public partial class Form2 : Form
    {
        public Form2(int nr, Color c)
        {
            InitializeComponent();
            label2.Text = nr.ToString();
            this.BackColor = c;
        }
    }
}
