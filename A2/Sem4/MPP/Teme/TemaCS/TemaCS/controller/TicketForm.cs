using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TemaCS.domain;
using TemaCS.service;

namespace TemaCS.controller
{
    public partial class TicketForm : Form
    {
        internal Show Show { private get; set; }
        internal Service Service { private get; set; }

        public TicketForm()
        {
            InitializeComponent();
            this.DialogResult= DialogResult.Cancel;
        }

        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            Utils.Execute(delegate
            {
                Service.SellTicket(Show, ((int)numericUpDownLocuri.Value), textBoxNume.Text);
                this.DialogResult= DialogResult.OK;
                this.Close();
            });
        }
    }
}
