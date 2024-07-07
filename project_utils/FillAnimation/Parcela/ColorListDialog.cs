using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Parcela
{
    public partial class ColorListDialog : Form
    {
        List<Color> list = new List<Color>();
        public List<Color> ColorList
        {
            get => list;
            set
            {
                list = value;
                flowLayoutPanel1.Controls.Clear();
                foreach(Color c in list)
                {
                    PictureBox box = new PictureBox();
                    box.Size = new Size(38, 36);
                    box.BackColor = c;
                    box.ContextMenuStrip = contextMenuStrip1;
                    flowLayoutPanel1.Controls.Add(box);
                }
            }
        }
        public ColorListDialog()
        {
            InitializeComponent();
        }

        private void PictureBox1_Click(object sender, EventArgs e)
        {
            if (colorDialog1.ShowDialog() == DialogResult.OK)
            {
                pictureBox2.BackColor = colorDialog1.Color;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            PictureBox box = new PictureBox();
            box.Size = new Size(38, 36);
            box.BackColor = pictureBox2.BackColor;
            box.ContextMenuStrip = contextMenuStrip1;
            flowLayoutPanel1.Controls.Add(box);
            list.Add(pictureBox1.BackColor);
        }

        private void ștergeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PictureBox box = ((sender as ToolStripMenuItem).Owner as ContextMenuStrip).SourceControl as PictureBox;
            list.Remove(box.BackColor);
            flowLayoutPanel1.Controls.Remove(box);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }
    }
}
