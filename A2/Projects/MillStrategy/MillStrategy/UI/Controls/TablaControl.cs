using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MillStrategy.Domain;
using MillStrategy.Structure;

namespace MillStrategy.UI.Controls
{
    public partial class TablaControl : UserControl
    {
        PictureBox pictureBoxTemp = null;
        List<PictureBox> pieseBox = null;
        List<PictureBox> PieseBox
        {
            get
            {
                if(pieseBox == null)
                {
                    pieseBox = new List<PictureBox>();
                    foreach (Control c in this.Controls)
                        if (c is PictureBox && c.Name != "TableBox")
                            pieseBox.Add(c as PictureBox);
                }
                return pieseBox;
            }
        }

        public Tabla Tabla { get; set; }

        public TablaControl()
        {
            InitializeComponent();
            foreach(PictureBox c in PieseBox)
            {
                c.Parent = TableBox;
            }
            Tabla = new Tabla();
            Tabla.PuneAfter += Pune;
            Tabla.IaBefore += Ia;
            Tabla.MutaStartAfter += MutaStart;
            Tabla.MutaStopAfter += MutaStop;
            //PunePieseDefault(Tabla);
            //Tabla.MillDetected += delegate(object sender, PozitiiEventArgs e){ MessageBox.Show(e.Pozitii.ToString());};
        }

        private void pictureBoxPiesaBlack_Paint(object sender, PaintEventArgs e)
        {
            PictureBox pictureBox = sender as PictureBox;
            int padding = 3;
            int w = pictureBox.Width, h = pictureBox.Height;
            e.Graphics.FillEllipse(Brushes.Black, new Rectangle(0+padding, 0+padding, w-padding, h-padding));
        }

        private void pictureBoxPiesaWhite_Paint(object sender, PaintEventArgs e)
        {
            PictureBox pictureBox = sender as PictureBox;
            int padding = 3;
            int w = pictureBox.Width, h = pictureBox.Height;
            e.Graphics.FillEllipse(Brushes.White, new Rectangle(0 + padding, 0 + padding, w - padding, h - padding));
        }

        private void pictureBoxPiesa_Click(object sender, EventArgs e)
        {
            PictureBox pictureBox = sender as PictureBox;
            Pozitie poz = new Pozitie(pictureBox.Tag as string);
            try { Tabla.ProcessPosition(poz); }
            catch(MyException ex) { MessageBox.Show(ex.Message); }
            //Tabla.ProcessPosition(poz);
        }

        private void RemovePaintEvent(PictureBox p, PiesaColor color)
        {
            if (color == PiesaColor.White) p.Paint -= pictureBoxPiesaWhite_Paint;            
            if (color == PiesaColor.Black) p.Paint -= pictureBoxPiesaBlack_Paint;            
        }

        private void PunePieseDefault(Tabla t)
        {
            for (OctalNumber nr = "10"; nr < "10" + new OctalNumber(16); nr++)
                t.ProcessPosition(new Pozitie(nr));
            t.ProcessPosition(new Pozitie("37")); // alb
            t.ProcessPosition(new Pozitie("33")); // negru
        }

        private void Pune(object sender, PozitieEventArgs e)
        {
            Pozitie poz = (Pozitie)e.Pozitie;
            PictureBox pictureBox = PieseBox.Where((PictureBox p) => p.Tag.ToString() == poz.Cod).First();
            PiesaColor color = Tabla.Piese[poz].PlayerColor;
            if (color == PiesaColor.White) pictureBox.Paint += pictureBoxPiesaWhite_Paint;
            if (color == PiesaColor.Black) pictureBox.Paint += pictureBoxPiesaBlack_Paint;
            pictureBox.Refresh();
        }

        private void Ia(object sender, PozitieEventArgs e)
        {
            Pozitie poz = (Pozitie)e.Pozitie;
            PictureBox pictureBox = PieseBox.Where((PictureBox p) => p.Tag.ToString() == poz.Cod).First();
            PiesaColor color = Tabla.Piese[poz].PlayerColor;
            if (color == PiesaColor.White) pictureBox.Paint -= pictureBoxPiesaWhite_Paint;
            if (color == PiesaColor.Black) pictureBox.Paint -= pictureBoxPiesaBlack_Paint;
            pictureBox.Refresh();
        }

        private void MutaStart(object sender, PozitieEventArgs e)
        {
            Pozitie poz = (Pozitie)e.Pozitie;
            pictureBoxTemp = PieseBox.Where((PictureBox p) => p.Tag.ToString() == poz.Cod).First();
            pictureBoxTemp.BorderStyle = BorderStyle.FixedSingle;
        }

        private void MutaStop(object sender, PozitieEventArgs e)
        {
            IPozitie poz = (Pozitie)e.Pozitie;
            poz = Tabla.GetPozitie(poz);
            PictureBox pictureBox = PieseBox.Where((PictureBox p) => p.Tag.ToString() == poz.Cod).First();
            RemovePaintEvent(pictureBoxTemp, Tabla.Piese[poz].PlayerColor);
            pictureBoxTemp.BorderStyle = BorderStyle.None;
            pictureBoxTemp.Refresh();
            PiesaColor color = Tabla.Piese[poz].PlayerColor;
            if (color == PiesaColor.White) pictureBox.Paint += pictureBoxPiesaWhite_Paint;
            if (color == PiesaColor.Black) pictureBox.Paint += pictureBoxPiesaBlack_Paint;
            pictureBox.Refresh();
        }
    }
}
