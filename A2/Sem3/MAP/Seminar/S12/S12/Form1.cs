using S12.comps;
using Sem11_12.Model;
using Sem11_12.Model.Validator;
using Sem11_12.Repository;
using Sem11_12.Service;

namespace S12
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            List<Angajat> angajati = GetAngajatService().FindAllAngajati();
            foreach(var angajat in angajati)
            {
                UserControlAngajat controlAngajat = new UserControlAngajat();
                controlAngajat.Angajat = angajat;
                flowLayoutPanel1.Controls.Add(controlAngajat);
            }

        }

        private static AngajatService GetAngajatService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["angajatiFileName"];
            string fileName = "..\\..\\..\\data\\angajati.txt";
            IValidator<Angajat> vali = new AngajatValidator();

            IRepository<string, Angajat> repo1 = new AngajatInFileRepository(vali, fileName);
            AngajatService service = new AngajatService(repo1);
            return service;
        }
    }
}