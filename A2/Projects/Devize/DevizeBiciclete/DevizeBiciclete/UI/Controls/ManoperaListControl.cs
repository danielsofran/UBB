using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Manopera = DevizeBiciclete.Domain.DevizData.ManoperaData;

namespace DevizeBiciclete.UI.Controls
{
    public partial class ManoperaListControl : UserControl
    {
        public ManoperaListControl()
        {
            InitializeComponent();
        }

        public List<Manopera> Manopere
        {
            get
            {
                List<Manopera> list = new List<Manopera>();
                foreach (ManoperaControl control in tableLayoutPanel4.Controls)
                    if (control.Valid) list.Add(control.Manopera);
                return list;
            }
            set
            {
                float height = tableLayoutPanel4.RowStyles[0].Height;
                int initheight = tableLayoutPanel4.Height;
                tableLayoutPanel4.Controls.Clear();
                tableLayoutPanel4.RowStyles.Clear();
                foreach (Manopera manopera in value)
                    addManopera(manopera, height);
                if (value.Count == 0) addManopera(new Manopera(), height);
                this.Height -= initheight;
            }
        }

        void addManopera(Manopera manopera, float height=0)
        {
            if(height == 0) height = tableLayoutPanel4.RowStyles[0].Height;
            tableLayoutPanel4.RowStyles.Add(new RowStyle(SizeType.Absolute, height));
            ManoperaControl control = new ManoperaControl();
            control.Dock = DockStyle.Fill;
            control.Manopera = manopera;
            control.ContextMenuStrip = contextMenuStrip1;
            tableLayoutPanel4.Controls.Add(control);
            this.Height += (int)height;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var lastpiesa = tableLayoutPanel4.Controls[tableLayoutPanel4.Controls.Count-1] as ManoperaControl;
            if(!lastpiesa.Valid)
            {
                MessageBox.Show("Ultima manopera nu este valida!");
                return;
            }
            addManopera(new Manopera());
        }

        private void stergeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem item = sender as ToolStripMenuItem; if (item == null) return;
            ContextMenuStrip contextMenuStrip = item.GetCurrentParent() as ContextMenuStrip; if (contextMenuStrip == null) return;
            ManoperaControl manoperaControl = contextMenuStrip.SourceControl as ManoperaControl; if (manoperaControl == null) return;
            if(tableLayoutPanel4.Controls.Count > 1)
            {
                tableLayoutPanel4.Controls.Remove(manoperaControl);
                Height -= manoperaControl.Height + manoperaControl.Margin.Top + manoperaControl.Margin.Bottom;
            }
        }

        private void duplicaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem item = sender as ToolStripMenuItem; if (item == null) return;
            ContextMenuStrip contextMenuStrip = item.GetCurrentParent() as ContextMenuStrip; if (contextMenuStrip == null) return;
            ManoperaControl manoperaControl = contextMenuStrip.SourceControl as ManoperaControl; if (manoperaControl == null) return;
            addManopera(manoperaControl.Manopera);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (tableLayoutPanel4.Controls.Count == 0) return;
            ManoperaControl ult = tableLayoutPanel4.Controls[tableLayoutPanel4.Controls.Count - 1] as ManoperaControl;

            foreach(ManoperaControl manoperaControl in tableLayoutPanel4.Controls)
                if(!manoperaControl.Valid && !manoperaControl.Equals(ult))
                {
                    MessageBox.Show("Manopera "+manoperaControl.Manopera.Nume+" este invalida!");
                    return;
                }
        }
    }
}
