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
    public partial class PbDialog : Form
    {
        public PbDialog()
        {
            InitializeComponent();
        }

        private void PbDialog_Load(object sender, EventArgs e)
        {
            webBrowser1.Url = new Uri(Application.StartupPath + "\\Parcela.html");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("https://www.pbinfo.ro/probleme/1369/parcela");
        }
    }
}
