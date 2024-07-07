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
    public partial class MainForm : Form
    {
        internal User User { private get; set; }
        internal Service Service { private get; set; } 

        public MainForm()
        {
            InitializeComponent();
            listViewArtisti.GridLines= true;
            listViewArtisti.FullRowSelect= true;
            listViewSpectacole.GridLines= true;
            listViewSpectacole.FullRowSelect= true;
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            listViewArtisti.Items.Clear();
            foreach (var show in Service.FindArtisti())
            {
                ListViewItem listViewItem = new ListViewItem(show.Artist.Name);
                listViewItem.SubItems.Add(show.Date.ToString("dd.MM.yyyy"));
                listViewItem.SubItems.Add(show.Location);
                listViewItem.SubItems.Add(show.AvailableSeats.ToString());
                listViewItem.SubItems.Add(show.SoldSeats.ToString());
                listViewItem.SubItems.Add(show.ID.ToString());
                listViewArtisti.Items.Add(listViewItem);
            }
        }

        private void buttonCauta_Click(object sender, EventArgs e)
        {
            listViewSpectacole.Items.Clear();
            foreach (var show in Service.FindShowsOnDate(dateTimePicker.Value))
            {
                ListViewItem listViewItem = new ListViewItem(show.Artist.Name);
                listViewItem.SubItems.Add(show.Location);
                listViewItem.SubItems.Add(show.BeginTime.ToString("hh:mm:ss"));
                listViewItem.SubItems.Add(show.AvailableSeats.ToString());
                listViewItem.Tag = show;
                listViewSpectacole.Items.Add(listViewItem);
            }
        }

        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            if(listViewSpectacole.SelectedItems.Count == 0)
            {
                MessageBox.Show("Spectacol neselectat!", "Atentie!");
                return;
            } 
                
            Show? show = listViewSpectacole.SelectedItems[0].Tag as Show;
            if(show == null) throw new InvalidProgramException("show null in tabel");

            TicketForm ticketForm = new TicketForm()
            {
                Show = show,
                Service = Service
            };
            if (ticketForm.ShowDialog() == DialogResult.OK)
            {
                MessageBox.Show("Biletul a fost rezervat.", "Succes!");
            }
            else
            {
                MessageBox.Show("Rezervare anulata.", "Fail");
            }
        }

        private void buttonLogout_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void MainForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            buttonLogout_Click(sender, e);
        }
    }
}
