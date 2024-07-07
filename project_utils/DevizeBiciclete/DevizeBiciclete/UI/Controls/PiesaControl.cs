using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Piesa = DevizeBiciclete.Domain.DevizData.PiesaData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class PiesaControl : UserControl
    {
        public PiesaControl()
        {
            InitializeComponent();
        }
        public bool Valid { get => textBoxNume.Text.Length > 0 && textBox1.Text.Length > 0 && numericUpDown1.Value != 0 && numericUpDown2.Value != 0; }
        public Piesa Piesa
        {
            get
            {
                Piesa piesa = new Piesa();
                piesa.Cod = textBoxNume.Text;
                piesa.Nume = textBox1.Text;
                piesa.NrBuc = (int)numericUpDown1.Value;
                piesa.Pret = (float)numericUpDown2.Value;
                return piesa;
            }
            set
            {
                textBoxNume.Text = value.Cod;
                textBox1.Text = value.Nume;
                numericUpDown1.Value = value.NrBuc;
                numericUpDown2.Value = (decimal)value.Pret;
            }
        }
    }
}
