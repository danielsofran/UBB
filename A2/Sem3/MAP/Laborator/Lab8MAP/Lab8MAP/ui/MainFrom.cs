using Lab8MAP.domain;
using Lab8MAP.exceptions;
using Lab8MAP.service;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab8MAP.ui
{
    public partial class MainFrom : Form
    {
        private delegate void UIAction();
        private Service service;

        private Echipa<int> EchipaSelectata
        {
            get
            {
                Echipa<int>? echipa = comboBoxEchipa.SelectedItem as Echipa<int>;
                if (echipa == null) throw new UIException("Echipa neselectata!");
                return echipa;
            }
        }

        private Meci<int> MeciSelectat
        {
            get
            {
                Meci<int>? Meci = listBoxMeciuri.SelectedItem as Meci<int>;
                if (Meci == null) throw new UIException("Meci neselectat!");
                return Meci;
            }
        }

        private void TryExecute(UIAction action)
        {
            try{ action.Invoke(); }
            catch (ApplicationException ex) { MessageBox.Show(ex.Message); }
        }

        public MainFrom()
        {
            InitializeComponent();
            service = new Service();
        }

        private void MainFrom_Load(object sender, EventArgs e)
        {
            comboBoxEchipa.Items.AddRange(service.Echipe);
            listBoxMeciuri.Items.AddRange(service.Meciuri);
            groupBoxMeciuri.Text = $"Meciuri: {service.Meciuri.Length}";
        }

        private void resetToolStripMenuItem_Click(object sender, EventArgs e)
        {
            var meciuri = service.Meciuri;
            listBoxMeciuri.Items.Clear();
            listBoxMeciuri.Items.AddRange(meciuri);
            groupBoxMeciuri.Text = $"Meciuri: {meciuri.Length}";
        }

        private void listBoxMeciuri_SelectedIndexChanged(object sender, EventArgs e)
        {
            UIAction action = () =>
            {
                labelMeci.Text = $"{MeciSelectat.Echipa1.Nume}{Environment.NewLine}{MeciSelectat.Echipa2.Nume}{Environment.NewLine}{MeciSelectat.Data.ToString("dd.MM.yyy")}";
                labelScor.Text = service.FindScor(MeciSelectat);
            };
            TryExecute(action);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            UIAction action = () =>
            {
                var jucatori = service.FindJucatoriiEchipei(EchipaSelectata);
                listBoxJucatori.Items.Clear();
                listBoxJucatori.Items.AddRange(jucatori);
                groupBoxJucatori.Text = $"Jucatori: {jucatori.Length}";
            };
            TryExecute(action);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            UIAction action = () =>
            {
                var jucatori = service.FindJucatoriiActiviLaMeciSiPuncteleInscrise(EchipaSelectata, MeciSelectat);
                listBoxJucatori.Items.Clear();
                listBoxJucatori.Items.AddRange(jucatori);
                groupBoxJucatori.Text = $"Jucatori: {jucatori.Length}";
            };
            TryExecute(action);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            UIAction action = () =>
            {
                var meciuri = service.FindMeciuriByDates(dateTimePickerFrom.Value, dateTimePickerTo.Value);
                labelScor.Text = "";
                labelMeci.Text = "-";
                listBoxMeciuri.Items.Clear();
                listBoxMeciuri.Items.AddRange(meciuri);
                groupBoxMeciuri.Text = $"Meciuri: {meciuri.Length}";
            };
            TryExecute(action);
        }
    }
}
