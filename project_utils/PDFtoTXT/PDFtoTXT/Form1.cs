namespace PDFtoTXT
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            comboBox1.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (comboBox1.SelectedIndex == 0)
            {
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    string rez = "";
                    textBox1.Clear();
                    foreach (var path in openFileDialog1.FileNames)
                    {
                        textBox1.AppendText(path + "; ");
                        //MessageBox.Show("Loading " + Path.GetFileName(path));
                        var txt = TextProcessor.PdfToTxt(path, false);
                        //MessageBox.Show("Loaded " + Path.GetFileName(path));
                        rez += txt;
                    }
                    textBox2.Text = rez;
                    if (checkBox1.Checked) System.Windows.Forms.Clipboard.SetText(rez);
                }
            }
            else
            {
                if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
                {
                    string path = folderBrowserDialog1.SelectedPath;
                    textBox1.Text = path;
                    var txt = TextProcessor.PdfToTxt(path, false);
                    textBox2.Text = txt;
                    if (checkBox1.Checked) System.Windows.Forms.Clipboard.SetText(txt);
                }
            }
        }
    }
}