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
    public partial class CodForm : Form
    {
        public CodForm()
        {
            InitializeComponent();
        }

        private void CodForm_Load(object sender, EventArgs e)
        {
            richTextBox1.LoadFile("cod.rtf");
        }
    }
}
