using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevizeBiciclete.Domain;

namespace DevizeBiciclete.UI.Controls
{
    public partial class ClientControl : UserControl
    {
        public ClientControl()
        {
            InitializeComponent();
            radioButton2_CheckedChanged(null, null);
            rdbtnfizica.Checked = true;
        }

        public bool Valid { get { return textBoxNume.Text.Length != 0 && textBoxTel.Text.Length != 0; } }
        public DevizData.ClientData Client
        {
            get
            {
                DevizData.ClientData client = new DevizData.ClientData();
                client.Nume = textBoxNume.Text;
                client.Telefon = textBoxTel.Text;
                client.PersoanaFizica = rdbtnfizica.Checked;
                if(client.PersoanaJuridica)
                {
                    client.Denumire = textBoxDen.Text;
                    client.RO = textBoxCIF.Text;
                    client.Registru = textBoxReg.Text;
                    client.Adresa = textBoxAdr.Text;
                    client.TelefonFrima = textBoxTelF.Text;
                }
                return client;
            }
            set
            {
                textBoxNume.Text = value.Nume;
                textBoxTel.Text=value.Telefon;
                rdbtnfizica.Checked = value.PersoanaFizica;
                if (value.PersoanaJuridica)
                {
                    textBoxDen.Text = value.Denumire;
                    textBoxCIF.Text = value.RO;
                    textBoxReg.Text = value.Registru;
                    textBoxAdr.Text = value.Adresa;
                    textBoxTelF.Text = value.TelefonFrima;
                }
            }
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            foreach(Control control in tableLayoutPanel1.Controls)
                if(control.Tag == "juridica")
                    control.Enabled = rdbtnjuridica.Checked;
        }

        private void textBoxTel_KeyPress(object sender, KeyPressEventArgs e)
        {
            if(! "1234567890+".Contains(e.KeyChar) && !char.IsControl(e.KeyChar))
                e.Handled = true;
        }

        private void textBoxNume_KeyPress(object sender, KeyPressEventArgs e)
        {
            if(!char.IsLetter(e.KeyChar) && e.KeyChar != ' ' && !char.IsControl(e.KeyChar))
                e.Handled= true;
        }

        private void textBox_KeyDown(object sender, KeyEventArgs e)
        {
            Control current = sender as Control;
            if (e.KeyCode == Keys.Enter)
                foreach (Control c in tableLayoutPanel1.Controls)
                    if (c.TabIndex == current.TabIndex + 1)
                        c.Focus();
        }
    }
}
