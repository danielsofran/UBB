using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Manopera = DevizeBiciclete.Domain.DevizData.ManoperaData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class ManoperaControl : UserControl
    {
        public ManoperaControl()
        {
            InitializeComponent();
        }
        public bool Valid { get => Manopera.Pret != 0 && Manopera.Nume.Length != 0 && Manopera.Durata !=0; }
        public Manopera Manopera
        {
            get
            {
                Manopera manopera = new Manopera();
                manopera.Nume = textBoxDen.Text;
                manopera.Durata = (float)numericUpDown2.Value;
                manopera.Pret = (float)numericUpDown1.Value;
                manopera.Discount = (float)numericUpDown3.Value;
                return manopera;
            }
            set
            {
                textBoxDen.Text = value.Nume;
                numericUpDown2.Value = (decimal)value.Durata;
                numericUpDown1.Value = (decimal)value.Pret;
                numericUpDown3.Value = (decimal)value.Discount;
            }
        }
    }
}
