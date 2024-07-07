using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Bicicleta = DevizeBiciclete.Domain.DevizData.BicicletaData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class BicicletaControl : UserControl
    {
        public BicicletaControl()
        {
            InitializeComponent();
        }

        public Bicicleta Bicicleta
        {
            get
            {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.Marca = textBoxDen.Text;
                bicicleta.Model = textBox1.Text;
                bicicleta.Culoare = textBox2.Text;
                bicicleta.Serie = textBox3.Text;
                return bicicleta;
            }
            set
            {
                textBoxDen.Text = value.Marca;
                textBox1.Text = value.Model;
                textBox2.Text = value.Culoare;
                textBox3.Text = value.Serie;
            }
        }
    }
}
