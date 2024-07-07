using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using bilete.model;
using bilete.services;
using client.controller;

namespace bilete.client
{
    public partial class TicketForm : Form
    {
        internal Show Show { private get; set; }
        internal BileteMainController Controller { private get; set; }

        public TicketForm()
        {
            InitializeComponent();
            this.DialogResult= DialogResult.Cancel;
        }

        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            int seats = (int)numericUpDownLocuri.Value;
            string costumerName = textBoxNume.Text;
            Ticket ticket = new Ticket(Show, costumerName, seats);
            try
            {
                Controller.sellTicket(ticket);
                this.Hide();
            }
            catch (BileteException ex) { MessageBox.Show(ex.Message); }

        }
    }
}
