using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Deviz = DevizeBiciclete.Domain.DevizData;

namespace DevizeBiciclete.UI
{
    public partial class DevizControl : UserControl
    {
        Deviz deviz;
        Repo.Repository repo;
        bool opened = false;

        public DevizControl()
        {
            InitializeComponent();
            deviz = new Deviz();
            repo = new Repo.Repository();
        }
        
        public Deviz Deviz
        {
            get => deviz;
            set
            {
                deviz = value;
                textBoxNume.Text = value.Client.Nume;
                textBoxTelefon.Text = value.Client.Telefon;
                textBoxIn.Text = value.Constatare.DataInText;
                textBoxOut.Text = value.Constatare.DataOutText;
                numericUpDownPret.Value = (decimal)value.TotalCuTVA;
                textBoxModel.Text = value.Bicicleta.Model;
            }
        }

        public Repo.Repository Repo { get => repo; set => repo = value; }

        private void deschideToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(opened)
            {
                MessageBox.Show("Ati deschis deja acest deviz!");
                return;
            }
            DevizForm devizForm = new DevizForm();
            devizForm.Deviz = this.Deviz;
            devizForm.ReadOnly = true;
            devizForm.FormClosing += delegate { opened = false; };
            opened = true;
            devizForm.Show();
        }

        private void modificareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (opened)
            {
                MessageBox.Show("Ati deschis deja acest deviz!");
                return;
            }
            DevizForm devizForm = new DevizForm();
            devizForm.Deviz = this.Deviz;
            devizForm.FormClosing += delegate
            {
                if(devizForm.DialogResult == DialogResult.OK)
                {
                    repo.Remove(this.Deviz);
                    this.Deviz = devizForm.Deviz.Clone();
                    repo.Add(Deviz);
                    this.Dispose();
                }
                opened = false;
            };
            opened = true;
            devizForm.Show();
        }

        private void stergeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(MessageBox.Show("Sigur doriti sa stergeti acest deviz?", "Alerta", MessageBoxButtons.YesNoCancel) == DialogResult.Yes)
            {
                // calls FUNCTION for repo delete
                repo.Remove(Deviz);
                this.Dispose();
                // add disposed method in upper class
            }
        }

        private void exportPDFToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ExportPDForm form = new ExportPDForm();
            form.Deviz = this.Deviz;
            form.ShowDialog();
        }

        private void duplicaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Deviz copie = Deviz.Clone();
            repo.Add(copie);
        }
    }
}
