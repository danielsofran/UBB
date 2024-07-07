using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Piesa = DevizeBiciclete.Domain.DevizData.PiesaData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class PiesaListControl : UserControl
    {
        public PiesaListControl()
        {
            InitializeComponent();
        }

        public List<Piesa> Piese
        {
            get
            {
                List<Piesa> list = new List<Piesa>();
                foreach(PiesaControl control in tableLayoutPanel3.Controls)
                    if (control.Valid) list.Add(control.Piesa);
                return list;
            }
            set
            {
                float height = tableLayoutPanel3.RowStyles[0].Height;
                int initheight = tableLayoutPanel3.Height;
                tableLayoutPanel3.Controls.Clear();
                tableLayoutPanel3.RowStyles.Clear();
                foreach (Piesa piesa in value)
                    addPiesa(piesa, height);
                if (value.Count == 0)
                    addPiesa(new Piesa(), height);
                this.Height -= initheight;
            }
        }

        void addPiesa(Piesa piesaData, float height=0)
        {
            if(height == 0) height = tableLayoutPanel3.RowStyles[0].Height;
            tableLayoutPanel3.RowStyles.Add(new RowStyle(SizeType.Absolute, height));
            PiesaControl piesa = new PiesaControl();
            piesa.Dock = DockStyle.Fill;
            piesa.Piesa = piesaData;
            piesa.ContextMenuStrip = contextMenuStrip1;
            tableLayoutPanel3.Controls.Add(piesa);
            this.Height += piesa.Height + piesa.Margin.Top + piesa.Margin.Bottom;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var lastpiesa = tableLayoutPanel3.Controls[tableLayoutPanel3.Controls.Count - 1] as PiesaControl;
            if (!lastpiesa.Valid)
            {
                MessageBox.Show("Ultima piesa nu este valida!");
                return;
            }
            addPiesa(new Piesa());
        }

        private void stergeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem item = sender as ToolStripMenuItem; if (item == null) return;
            ContextMenuStrip contextMenuStrip = item.GetCurrentParent() as ContextMenuStrip; if (contextMenuStrip == null) return;
            PiesaControl piesaControl = contextMenuStrip.SourceControl as PiesaControl; if (piesaControl == null) return;
            if (tableLayoutPanel3.Controls.Count >1) { 
                tableLayoutPanel3.Controls.Remove(piesaControl);
                Height -= piesaControl.Height + piesaControl.Margin.Top + piesaControl.Margin.Bottom;
            }
        }

        private void duplicaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem item = sender as ToolStripMenuItem; if (item == null) return;
            ContextMenuStrip contextMenuStrip = item.GetCurrentParent() as ContextMenuStrip; if (contextMenuStrip == null) return;
            PiesaControl piesaControl = contextMenuStrip.SourceControl as PiesaControl; if (piesaControl == null) return;
            addPiesa(piesaControl.Piesa);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (tableLayoutPanel3.Controls.Count == 0) return;
            PiesaControl ult = tableLayoutPanel3.Controls[tableLayoutPanel3.Controls.Count - 1] as PiesaControl;

            foreach (PiesaControl piesa in tableLayoutPanel3.Controls)
                if (!piesa.Valid && !piesa.Equals(ult))
                {
                    MessageBox.Show("Piesa " + piesa.Piesa.Nume + " este invalida!");
                    return;
                }
        }
    }
}
