using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Input;
using System.Threading;
using System.IO;

namespace Parcela
{
    public partial class Form1 : Form
    {
        // general
        //int x = 33, y = 99, space = 5;
        int n, m;
        Button[,] mat;
        Color alb, apa = Color.Aqua;
        int ALEGE = 1, SCHIMBA = 2, COLOREAZA = 3;
        int act = 3, ales = 0;
        // fill
        int[] di = { 1, 0, -1, 0 };
        int[] dj = { 0, 1, 0, -1 };
        bool[] marcat;

        //animatii
        Queue<Action> AnimationQueue = new Queue<Action>();
        bool Animating = false;
        int speed = 100;
        int val = 50;

        // culori
        public List<Color> v { get; set; } = new List<Color> { Color.Blue, Color.DarkGray, Color.Green, Color.Magenta, Color.FromArgb(200, 255, 102, 0), Color.LawnGreen, Color.Red, Color.Yellow };

        private void SchimbaCuClickToolStripMenuItem_Click(object sender, EventArgs e) { act = 2; label2.Text = "Schimba\nelementele\n cu click"; }
        private void ToolStripMenuItem2_Click(object sender, EventArgs e) { act = 1; ales = 1; label2.Text = "Schimba in 1"; }
        private void ToolStripMenuItem3_Click(object sender, EventArgs e) { act = 1; ales = 0; label2.Text = "Schimba in 0"; }
        private void ColoreazaToolStripMenuItem1_Click(object sender, EventArgs e) { act = 3; label2.Text = "Coloreaza"; }

        private void SchimbaCuloareaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (colorDialog1.ShowDialog() == DialogResult.OK)
            {
                pictureBox1.BackColor = colorDialog1.Color;
            }
        }

        private void PictureBox1_Click(object sender, EventArgs e)
        {
            if (colorDialog1.ShowDialog() == DialogResult.OK)
            {
                pictureBox1.BackColor = colorDialog1.Color;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            openFileDialog1.FileName = "parcela.txt";
            openFileDialog1.InitialDirectory = Application.StartupPath;
            if (openFileDialog1.ShowDialog() != DialogResult.OK) return;
            StreamReader fin = new StreamReader(openFileDialog1.FileName);
            string s = fin.ReadLine();
            string[] v = s.Split();
            n = int.Parse(v[0]);
            m = int.Parse(v[1]);
            if (n > 32 || m > 32) { MessageBox.Show("Numarul de linii/coloane este prea mare."); return; }
            numericUpDown1.Value = n;
            numericUpDown2.Value = m;
            if (n == 0 || m == 0) { MessageBox.Show("Date incorecte!"); return; }
            mat = new Button[n, m];
            flowLayoutPanel1.Size = new Size(Math.Min(50 * m + 50 * 3, this.Width - flowLayoutPanel1.Left - val), Math.Min(50 * n + 50 * 3, this.Height - flowLayoutPanel1.Top - val));
            flowLayoutPanel1.Controls.Clear();
            for (int i = 0; i < n; ++i)
            {
                s = fin.ReadLine();
                v = s.Split();
                for (int j = 0; j < m; ++j)
                {
                    mat[i, j] = new Button();
                    mat[i, j].Text = v[j];
                    mat[i, j].Name = i + " " + j;
                    //mat[i, j].Location = new Point(x, y);
                    mat[i, j].Size = new Size(50, 50);
                    mat[i, j].Click += CLICK;
                    mat[i, j].MouseEnter += ENTER;
                    toolTip1.SetToolTip(mat[i, j], (i+1) + "," + (j+1));
                    //mat[i, j].BackColorChanged += Delay;
                    flowLayoutPanel1.Controls.Add(mat[i, j]);
                    //this.Refresh();
                    //Thread.Sleep(100);
                }
                flowLayoutPanel1.SetFlowBreak(mat[i, m - 1], true);
            }
            //flowLayoutPanel1.Size = new Size(mat[n-1, m-1].Left + mat[n - 1, m - 1].Width + mat[n - 1, m - 1].Margin.Right, mat[n - 1, m - 1].Top + mat[n - 1, m - 1].Height + mat[n - 1, m - 1].Margin.Bottom);
            alb = mat[0, 0].BackColor;
            this.Height = flowLayoutPanel1.Top + flowLayoutPanel1.Height + 10;
        }

        private void CLICK(object sender, EventArgs e)
        {
            Button b = (Button)sender;
            if (act == SCHIMBA)
            {
                if (b.Text == "1") b.Text = "0";
                else b.Text = "1";
            }
            else if (act == COLOREAZA)
            {
                Point poz = decode(b.Name);
                Color c = pictureBox1.BackColor;
                Fill(poz.X, poz.Y, c);
            }
        }

        Random r = new Random();
        void autofill1()
        {
            marcat = new bool[v.Count];
            System.Windows.Forms.Timer timer = new System.Windows.Forms.Timer();
            timer.Interval = 1;
            int i = 0, j = 0;
            timer.Tick += delegate
            {
                if (!Animating)
                {
                    if (mat[i, j].Text == "0" && mat[i, j].BackColor != apa)
                        Fill(i, j, apa);
                    else if (mat[i, j].BackColor == alb)
                    {
                        int p;
                        if (marcat.Contains(false) && marcat.ToList().IndexOf(false) != marcat.ToList().LastIndexOf(false))
                        {
                            while (marcat[p = r.Next(0, v.Count - 1)]) p = r.Next(0, v.Count - 1);
                        }
                        else
                        {
                            for (int k=0;k<marcat.Length;++k) marcat[k] = false;
                            p = r.Next(0, v.Count - 1);
                        }
                        marcat[p] = true;
                        Color c = v[p];
                        Fill(i, j, c);
                    }

                    if (j < m - 1) ++j;
                    else if (i < n - 1) { j = 0; ++i; }
                    else timer.Stop();
                }
            };
            timer.Start();
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            if (n != 0 && m != 0)
            {
                decolor();
                autofill1();
            }
            else MessageBox.Show("Setati prima data matricea.");
        }

        private void ENTER(object sender, EventArgs e)
        {
            if (act == ALEGE)
            {
                Button b = (Button)sender;
                b.Text = ales.ToString();
            }
        }

        private void Button4_Click(object sender, EventArgs e)
        {
            if (n == 0 || m == 0) return;
            System.Windows.Forms.Timer timer = new System.Windows.Forms.Timer();
            timer.Interval = 1;
            int i = 0, j = 0;
            timer.Tick += delegate
            {
                if (!Animating)
                {
                    Fill(i, j, alb);
                    if (j < m - 1) ++j;
                    else if (i < n - 1) { j = 0; ++i; }
                    else timer.Stop();
                }
            };
            timer.Start();
        }

        bool ok(int i, int j) { return i >= 0 && j >= 0 && i < n && j < m; }

        private void AriaMaximaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            int nr=0;
            Color c=Color.White;
            amax(ref nr, ref c);
            Form2 f = new Form2(nr, c);
            f.ShowDialog();
        }

        void FillInstant(int x, int y, Color c)
        {
            mat[x, y].BackColor = c;
            //if (mat[x, y].Text == "0") apa = c;

            for(int k=0;k<4;++k)
            {
                int i = x + di[k];
                int j = y + dj[k];
                if (ok(i, j) && mat[i, j].BackColor != c && mat[i, j].Text == mat[x, y].Text)
                    FillInstant(i, j, c);
            }
        }

        void Fill_0(int x, int y, Color c)
        {
            System.Windows.Forms.Timer timer = new System.Windows.Forms.Timer();
            timer.Interval = speed;
            Animating = true;
            //if (mat[x, y].Text == "0") apa = c;
            Stack<Point> q = new Stack<Point>();
            q.Push(new Point(x, y));
            timer.Tick += delegate
            {
                if (q.Count > 0)
                {
                    Point p = q.Pop();
                    mat[p.X, p.Y].BackColor = c;
                    for (int k = 0; k < 4; ++k)
                    {
                        int i = p.X + di[k];
                        int j = p.Y + dj[k];
                        if (ok(i, j) && mat[i, j].BackColor != c && mat[i, j].Text == mat[p.X, p.Y].Text)
                            q.Push(new Point(i, j));
                    }
                }
                else { timer.Stop(); Animating = false; ProceedNextInQueue(); }
            };
            timer.Start();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            n = (int)numericUpDown1.Value;
            m = (int)numericUpDown2.Value;
            if (n == 0 || m == 0) { flowLayoutPanel1.Controls.Clear(); return; }
            mat = new Button[n, m];
            flowLayoutPanel1.Size = new Size(Math.Min(50 * m + 50 * 3, this.Width - flowLayoutPanel1.Left - val), Math.Min(50 * n + 50 * 3, this.Height - flowLayoutPanel1.Top-val));
            flowLayoutPanel1.Controls.Clear();
            for (int i = 0; i < n; ++i)
            {
                for (int j = 0; j < m; ++j)
                {
                    mat[i, j] = new Button();
                    mat[i, j].Text = "0";
                    mat[i, j].Name = i + " " + j;
                    //mat[i, j].Location = new Point(x, y);
                    mat[i, j].Size = new Size(50, 50);
                    mat[i, j].Click += CLICK;
                    mat[i, j].MouseEnter += ENTER;
                    toolTip1.SetToolTip(mat[i, j], (i + 1) + "," + (j + 1));
                    //mat[i, j].BackColorChanged += Delay;
                    flowLayoutPanel1.Controls.Add(mat[i, j]);
                    //this.Refresh();
                    //Thread.Sleep(100);
                }
                flowLayoutPanel1.SetFlowBreak(mat[i, m - 1], true);
            }
            //flowLayoutPanel1.Size = new Size(mat[n-1, m-1].Left + mat[n - 1, m - 1].Width + mat[n - 1, m - 1].Margin.Right, mat[n - 1, m - 1].Top + mat[n - 1, m - 1].Height + mat[n - 1, m - 1].Margin.Bottom);
            alb = mat[0, 0].BackColor;
            this.Height = flowLayoutPanel1.Top + flowLayoutPanel1.Height + 10;
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedIndex == 0) speed = 500;
            else if (comboBox1.SelectedIndex == 1) speed = 250;
            else if (comboBox1.SelectedIndex == 2) speed = 100;
            else if (comboBox1.SelectedIndex == 3) speed = 50;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            comboBox1.SelectedIndex = 2;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            ColorListDialog dialog = new ColorListDialog();
            dialog.ColorList = v;
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                v = dialog.ColorList;
            }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            PbDialog dialog = new PbDialog();
            dialog.Show();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            CodForm form = new CodForm();
            form.Show();
        }

        void Fill(int x, int y, Color c)
        {
            if (!Animating)
            {
                if (comboBox1.SelectedItem == "Instant") FillInstant(x, y, c);
                else Fill_0(x, y, c);
            }
            else AnimationQueue.Enqueue(() => Fill_0(x, y, c));
        }

        void Fillc(int x, int y, ref int nr)
        {
            mat[x, y].Tag = "1";
            ++nr;
            for (int k = 0; k < 4; ++k)
            {
                int i = x + di[k];
                int j = y + dj[k];
                if (ok(i, j) && mat[i, j].Tag != "1" && mat[i, j].Text == mat[x, y].Text)
                    Fillc(i, j, ref nr);
            }
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            flowLayoutPanel1.Size = new Size(Math.Min(50 * m + 50 * 3, this.Width - flowLayoutPanel1.Left-val), Math.Min(50 * n + 50 * 3, this.Height - flowLayoutPanel1.Top-val));
        }

        Point decode(string s)
        {
            string[] v = s.Split();
            int i = int.Parse(v[0]);
            int j = int.Parse(v[1]);
            return new Point(i, j);
        }

        void decolor()
        {
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    mat[i, j].BackColor = alb;
        }

        void amax(ref int mx, ref Color c)
        {
            mx = -1;
            for(int i=0;i<n;++i)
                for(int j=0;j<m;++j)
                {
                    if(mat[i, j].Tag!="1" && mat[i, j].Text == "1")
                    {
                        int nr = 0;
                        Fillc(i, j, ref nr);
                        if (nr > mx) { mx = nr; c = mat[i, j].BackColor; }
                    }
                }
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    mat[i, j].Tag = "";
        }

        public Form1()
        {
            InitializeComponent();
        }

        void ProceedNextInQueue() { if (AnimationQueue.Count > 0) AnimationQueue.Dequeue().Invoke();}
    }
}
