using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevizeBiciclete.Domain;
using Deviz = DevizeBiciclete.Domain.DevizData;


namespace DevizeBiciclete.UI
{
    public partial class ExportPDForm : Form
    {
        Deviz deviz;
        
        public ExportPDForm()
        {
            InitializeComponent();
            deviz = new Deviz();
            textBox1.Text = DevizSetari.PDFPath;
        }

        public Deviz Deviz { get { return deviz; } set { deviz = value; numericUpDown1.Value = 0; } }

        private string getPath()
        {
            string dir = textBox1.Text;
            if (dir.Last() != '\\') dir += '\\';
            dir += textBox2.Text;
            if (!dir.EndsWith(".pdf")) dir += ".pdf";
            return dir;
        }

        private void textBox2_Resize(object sender, EventArgs e)
        {
            textBox2.Margin = new Padding(textBox2.Margin.Left, textBox2.Margin.Top, (int)tableLayoutPanel2.ColumnStyles[1].Width, textBox2.Margin.Bottom);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialog.ShowDialog() == DialogResult.OK)
            {
                textBox1.Text = folderBrowserDialog.SelectedPath;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string pdfpath = Application.StartupPath + @"vizualizare.pdf";
            try
            {
                deviz.ToPDF(pdfpath, (int)numericUpDown1.Value, (int)numericUpDown2.Value);
                Process process = new Process();
                process.StartInfo.FileName = "explorer.exe";
                process.StartInfo.UseShellExecute = false;
                process.StartInfo.RedirectStandardOutput = true;
                process.StartInfo.RedirectStandardError = true;
                process.StartInfo.Arguments = pdfpath;
                process.Start();
                process.WaitForExit();
                process.Close();
            }
            catch (IOException)
            {
                MessageBox.Show("Va rugam sa inchideti vizualizarea precedenta!");
            }
            catch(Exception ex)
            {
                MessageBox.Show("A aparut o alta eroare! Codul erorii este:\n" + ex.Message);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            string pdfpath = textBox1.Text;
            if (!Directory.Exists(textBox1.Text))
            {
                MessageBox.Show("Director inexistent!");
                return;
            }
            if(textBox2.Text.Length == 0)
            {
                MessageBox.Show("Numele este vid!");
                return;
            }
            //if(File.Exists(pdfpath))
            //{
            //    if(MessageBox.Show("Acest fisier exista!\nDoriti sa il suprascrieti?", "Fisierul exista deja", MessageBoxButtons.YesNoCancel) != DialogResult.Yes)
            //    {
            //        return;
            //    }
            //}
            try
            {
                deviz.ToImgs(pdfpath, textBox2.Text, (int)numericUpDown1.Value, (int)numericUpDown2.Value);
                if(checkBox1.Checked) {
                    Process process = new Process();
                    process.StartInfo.FileName = "explorer.exe";
                    process.StartInfo.UseShellExecute = false;
                    process.StartInfo.RedirectStandardOutput = true;
                    process.StartInfo.RedirectStandardError = true;
                    process.StartInfo.Arguments = pdfpath;
                    process.Start();
                    process.WaitForExit();
                    process.Close();
                }
                this.DialogResult = DialogResult.OK;
                DevizSetari.PDFPath = textBox1.Text;
                this.Close();
            }
            catch (IOException)
            {
                MessageBox.Show("Va rugam sa inchideti acest document, daca il aveti deschis!");
                return;
            }
            catch (Exception ex)
            {
                MessageBox.Show("A aparut o alta eroare! Codul erorii este:\n" + ex.Message);
                return;
            }
        }
    }
}
