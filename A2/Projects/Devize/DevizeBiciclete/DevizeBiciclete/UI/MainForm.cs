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

namespace DevizeBiciclete.UI
{
    public partial class MainForm : Form
    {
        Repository repo;
        List<Func<Deviz, bool>> lambdas;
        
        public MainForm()
        {
            InitializeComponent();
            if (!File.Exists(Application.StartupPath + "repository.data"))
            {
                ///////////////////////////////////////// COPY the copy file
                repo = new Repository();
                repo.Path = Application.StartupPath + "repository.data";
                repo.ToFile();
            }
            else
            {
                repo = Repository.FromFile(Application.StartupPath + "repository.data");
                foreach (Deviz deviz in repo.ToList)
                    addDeviz(deviz);
            }
            repo.ItemsAdded += repo_ItemAdded;
        }

        void addDeviz(Deviz deviz, Repository repository = null)
        {
            DevizControl devizControl = new DevizControl();
            devizControl.Size = new Size(550, 200);
            devizControl.Deviz = deviz;
            devizControl.Repo = repo;
            flowLayoutPanel1.Controls.Add(devizControl);
        }

        void repo_ItemAdded(object sender, RepositoryEventArgs e)
        {
            addDeviz(e.Deviz);
            if(e.Deviz.TVA != DevizSetari.TVA)
                DevizSetari.TVA = e.Deviz.TVA;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DevizForm devizForm = new DevizForm();
            devizForm.DevizId = repo.LastNumar + 1;
            if (devizForm.ShowDialog() == DialogResult.OK)
            {
                repo.Add(devizForm.Deviz);
            }
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            try { repo.ToFile(); } catch(Exception ex) { MessageBox.Show("Nu am putut salva devizele!\n"+ex.Message); }

            if (DevizSetari.IsTimeToBackup()) CreateBackup();
            try { DevizSetari.ToFile(Application.StartupPath + "setari.settings"); } catch { }
        }

        private void comboBoxFiltru_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (repo is null) return;
            AutoCompleteStringCollection collection = new AutoCompleteStringCollection();
            foreach (Deviz deviz in repo.ToList)
                switch (comboBoxFiltru.SelectedItem.ToString())
                {
                    case "Numar deviz":
                        collection.Add(deviz.Numar.ToString());
                        break;
                    case "Nume":
                        collection.Add(deviz.Client.Nume);
                        foreach (string token in deviz.Client.Nume.Split())
                            collection.Add(token);
                        break;
                    case "Telefon":
                        collection.Add(deviz.Client.Telefon);
                        break;
                    case "Data in":
                        collection.Add(deviz.Constatare.DataInText);
                        break;
                    case "Data out":
                        collection.Add(deviz.Constatare.DataOutText);
                        break;
                    case "Model bicicleta":
                        collection.Add(deviz.Bicicleta.Model);
                        break;
                    case "Marca bicicleta":
                        collection.Add(deviz.Bicicleta.Marca);
                        break;
                    case "Culoare bicicleta":
                        collection.Add(deviz.Bicicleta.Culoare);
                        break;
                    case "Serie bicicleta":
                        collection.Add(deviz.Bicicleta.Serie);
                        break;
                }
            textBox1.AutoCompleteCustomSource = collection;
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            comboBoxFiltru.SelectedIndex = 0;
            comboBoxFiltru_SelectedIndexChanged(this, EventArgs.Empty);
            lambdas = new List<Func<Deviz, bool>>(10);
            lambdas.Add((deviz) => deviz.Client.Nume.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Client.Telefon.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Constatare.DataInText.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Constatare.DataOutText.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Numar.ToString().ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Bicicleta.Model.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Bicicleta.Marca.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Bicicleta.Culoare.ToLower().Contains(textBox1.Text.ToLower()));
            lambdas.Add((deviz) => deviz.Bicicleta.Serie.ToLower().Contains(textBox1.Text.ToLower()));
        }

        private void buttonCauta_Click(object sender, EventArgs e)
        {
            var lambda = lambdas[comboBoxFiltru.SelectedIndex];
            foreach (DevizControl control in flowLayoutPanel1.Controls)
                if (!lambda(control.Deviz))
                    control.Visible = false;
                else control.Visible = true;
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (textBox1.Text.Length == 0)
            {
                foreach (DevizControl control in flowLayoutPanel1.Controls)
                    control.Visible = true;
            }
        }

        private void textBox1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                e.Handled = true;
                buttonCauta_Click(sender, e);
                this.ActiveControl = null;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                try { File.Copy(repo.Path, saveFileDialog1.FileName + ".copy", true); }
                catch(Exception ex) { MessageBox.Show("Eroare!\n"+ex.Message); }
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    Repository repoext = Repository.FromFile(openFileDialog1.FileName);
                    foreach (Deviz deviz in repoext.ToList)
                        if (!repo.Contains(deviz))
                        {
                            repo.Add(deviz);
                        }
                } 
                catch(Exception ex)
                {
                    MessageBox.Show("Eroare la deschiderea fisierului!");
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            repo = Repository.FromFile(Application.StartupPath + "repository.data");
            flowLayoutPanel1.Controls.Clear();
            foreach (Deviz deviz in repo.ToList)
                addDeviz(deviz);
        }

        private void CreateBackup()
        {
            repo.ToFile(DevizSetari.BackupFileName);
            DevizSetari.LastBackup = DateTime.Now;
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            CreateBackup();
            MessageBox.Show("Backup creeat!");
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Repository fromBackup = Repository.FromFile(DevizSetari.BackupFileName);
            repo.MergeWith(fromBackup);
        }
    }
}
