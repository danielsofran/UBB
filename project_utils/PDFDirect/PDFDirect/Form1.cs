using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.IO;
using System.Windows.Forms;
using Aspose.Pdf;

namespace PDFDirect
{
    public partial class Form1 : Form
    {
        string filename = Application.StartupPath + @"\_directpdf.txt";
        public Form1()
        {
            InitializeComponent();
            if (!File.Exists(filename))
                File.Create(filename);
            this.Icon = PDFDirect.Properties.Resources.pdf;
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            StreamWriter fout = new StreamWriter(filename);
            fout.WriteLine(textBox1.Text);
            fout.WriteLine(textBox2.Text);
            fout.Close();
        }

        private void Form1_Shown(object sender, EventArgs e)
        {
            try
            {
                StreamReader fin = new StreamReader(filename);
                textBox1.Text = fin.ReadLine();
                textBox2.Text = Application.StartupPath + "\\";
                textBox2.Text = fin.ReadLine();
                fin.Close();
            }
            catch (Exception ex) { }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog d = new FolderBrowserDialog();
            if(d.ShowDialog()==DialogResult.OK)
            {
                textBox1.Text = d.SelectedPath;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog d = new FolderBrowserDialog();
            if (d.ShowDialog() == DialogResult.OK)
            {
                textBox2.Text = d.SelectedPath;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            List<Tuple<Document, string>> l = Convertor.FromDirectory(textBox1.Text, checkBox1.Checked);
            string rez = "";
            foreach(Tuple<Document, string> d in l)
            {
                d.Item1.Save(textBox2.Text + "\\" + d.Item2);
                rez += textBox2.Text + "\\" + d.Item2 + "\n";
            }
            if(checkBox2.Checked)
                MessageBox.Show("Documentele " + rez + "s-au salvat cu success!");
            this.Close();
        }
    }
}
