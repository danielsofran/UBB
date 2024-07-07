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
    public partial class MainForm : Form
    {
        private readonly BileteMainController controller;
        private readonly IEnumerable<Show> artistList;
        private IEnumerable<Show> showList;
        

        public MainForm(BileteMainController controller)
        {
            InitializeComponent();
            listViewArtisti.GridLines= true;
            listViewArtisti.FullRowSelect= true;
            listViewSpectacole.GridLines= true;
            listViewSpectacole.FullRowSelect= true;

            this.controller = controller;
            this.artistList = controller.findAllArtisti();
            this.showList = new List<Show>();
            controller.TicketSold += ticketUpdate;
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            loadArtists();
        }

        private void buttonCauta_Click(object sender, EventArgs e)
        {
            DateTime dateTime = dateTimePicker.Value;
            try
            {
                showList = controller.findShowsOnDate(dateTime);
                loadShows();
            }
            catch (BileteException ex) { MessageBox.Show(ex.Message); }
        }


        public void ticketUpdate(object sender, TicketEventArgs ticketEventArgs)
        {
            Ticket ticket = ticketEventArgs.Ticket;
            foreach(var artist in artistList)
            {
                if(ticket.Show.ID == artist.ID)
                {
                    Console.WriteLine("Updating GUI of show " + artist);
                    artist.SoldSeats += ticket.Seats;
                    artist.AvailableSeats -= ticket.Seats;
                    break;
                }
            }
            foreach (var show in showList)
            {
                if (ticket.Show.ID == show.ID)
                {
                    Console.WriteLine("Updating GUI of show " + show);
                    show.SoldSeats += ticket.Seats;
                    show.AvailableSeats -= ticket.Seats;
                    break;
                }
            }

            listViewArtisti.Invoke( () => loadArtists());
            listViewSpectacole.Invoke( () => loadShows());
        }


        // TODO
        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            if (listViewSpectacole.SelectedItems.Count == 0)
            {
                MessageBox.Show("Spectacol neselectat!", "Atentie!");
                return;
            }

            Show? show = listViewSpectacole.SelectedItems[0].Tag as Show;
            if (show == null) throw new InvalidProgramException("show null in tabel");

            TicketForm ticketForm = new TicketForm()
            {
                Show = show,
                Controller= controller,
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
            Console.WriteLine("MainWindow closing ");
            controller.logout();
            controller.TicketSold -= ticketUpdate;
            Application.Exit();
        }

        private void MainForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            buttonLogout_Click(sender, e);
        }

        private void loadArtists()
        {
            listViewArtisti.Items.Clear();
            foreach (var show in artistList)
            {
                ListViewItem listViewItem = new ListViewItem(show.Artist.Name);
                listViewItem.SubItems.Add(show.Date.ToString("dd.MM.yyyy"));
                listViewItem.SubItems.Add(show.Location);
                listViewItem.SubItems.Add(show.AvailableSeats.ToString());
                listViewItem.SubItems.Add(show.SoldSeats.ToString());
                listViewItem.SubItems.Add(show.ID.ToString());
                if (show.AvailableSeats == 0)
                    listViewItem.BackColor = Color.RebeccaPurple;
                listViewArtisti.Items.Add(listViewItem);
            }
        }

        private void loadShows()
        {
            listViewSpectacole.Items.Clear();
            foreach (var show in showList)
            {
                ListViewItem listViewItem = new ListViewItem(show.Artist.Name);
                listViewItem.SubItems.Add(show.Location);
                listViewItem.SubItems.Add(show.BeginTime.ToString("hh:mm:ss"));
                listViewItem.SubItems.Add(show.AvailableSeats.ToString());
                listViewItem.Tag = show;
                if (show.AvailableSeats == 0)
                    listViewItem.BackColor = Color.RebeccaPurple;
                listViewSpectacole.Items.Add(listViewItem);
            }
        }
    }
}
