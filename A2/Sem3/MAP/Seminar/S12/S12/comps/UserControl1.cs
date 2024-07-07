using Sem11_12.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace S12.comps
{
    public partial class UserControlAngajat : UserControl
    {
        private Angajat angajat;

        public UserControlAngajat()
        {
            InitializeComponent();
        }
        
        public Angajat Angajat
        {
            get => angajat;
            set
            {
                angajat = value;
                labelNume.Text = angajat.Nume;
                labelNivel.Text = angajat.Nivel.ToString();
                labelVenit.Text = angajat.VenitPeOra.ToString();
            }
        }
    }
}
