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
using DevizeBiciclete.Repo;
using Deviz = DevizeBiciclete.Domain.DevizData;
using Piesa = DevizeBiciclete.Domain.DevizData.PiesaData;
using Manopera = DevizeBiciclete.Domain.DevizData.ManoperaData;
using Constatare = DevizeBiciclete.Domain.DevizData.ConstatareData;
using Bicicleta = DevizeBiciclete.Domain.DevizData.BicicletaData;
using Client = DevizeBiciclete.Domain.DevizData.ClientData;
using Piese = System.Collections.Generic.List<DevizeBiciclete.Domain.DevizData.PiesaData>;
using Manopere = System.Collections.Generic.List<DevizeBiciclete.Domain.DevizData.ManoperaData>;

namespace DevizeBiciclete.UI
{
    public partial class DevizForm : Form
    {

        public DevizForm()
        {
            InitializeComponent();
            numericUpDownTVA.Value = (decimal)(DevizSetari.TVA * 100);
        }

        public bool ReadOnly
        {
            get => !buttonSalveaza.Enabled;
            set { buttonSalveaza.Enabled = !value; buttonSalveaza.Visible = false; }
        }

        public bool Valid
        {
            get
            {
                if (!clientControl.Valid)
                {
                    MessageBox.Show("Client invalid!");
                    return false;
                }
                if (!constatareControl.Valid)
                {
                    MessageBox.Show("Constatare invalida!");
                    return false;
                }
                return true;
            }
        }

        public Deviz Deviz { 
            get
            {
                Deviz deviz = new Deviz();
                deviz.TVA = (float)numericUpDownTVA.Value / 100;
                deviz.Numar = (long)numericUpDownNumar.Value;
                deviz.Client = clientControl.Client;
                deviz.Bicicleta = bicicletaControl.Bicicleta;
                deviz.Constatare = constatareControl.Constatare;
                deviz.Piese = piesaListControl.Piese;
                deviz.Manopere = manoperaListControl.Manopere;
                return deviz;
            }
            set
            {
                numericUpDownTVA.Value = (decimal)value.TVA * 100;
                numericUpDownNumar.Value = value.Numar;
                clientControl.Client = value.Client;
                bicicletaControl.Bicicleta = value.Bicicleta;
                constatareControl.Constatare = value.Constatare;
                piesaListControl.Piese = value.Piese;
                manoperaListControl.Manopere = value.Manopere;
            }
        }

        private void manoperaListControl_Resize(object sender, EventArgs e)
        {
            Control control = (Control)sender; if (control == null) return;
            TableLayoutPanel panel = control.Parent as TableLayoutPanel; if (panel == null) return;
            TableLayoutPanelCellPosition pos = panel.GetPositionFromControl(control);
            RowStyle row = panel.RowStyles[pos.Row];
            if (control.Height > row.Height)
            {
                var dif = control.Height - row.Height + control.Margin.Bottom + control.Margin.Top;
                row.Height += (int)dif;
                panel.Height += (int)dif;
            }
            else
            {
                var dif = -control.Height + row.Height;
                row.Height = control.Height + control.Margin.Bottom + control.Margin.Top;
                panel.Height -= (int)dif - (control.Margin.Bottom + control.Margin.Top);
            }
            this.Refresh();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (Valid)
            {
                this.DialogResult = DialogResult.OK;
                this.Close();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.DialogResult=DialogResult.Cancel;
            this.Close();
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            if(this.Valid)
            {
                ExportPDForm form = new ExportPDForm();
                form.Deviz = this.Deviz;
                form.ShowDialog();
            }
        }
    }
}
