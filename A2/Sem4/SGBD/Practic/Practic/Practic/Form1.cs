using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Practic
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private SqlConnection conn;
        private DataSet dataSet = new DataSet();
        private SqlDataAdapter curieriAdapter = new SqlDataAdapter();
        private SqlDataAdapter comenziAdapter = new SqlDataAdapter();
        private BindingSource curieriBindingSource = new BindingSource();
        private BindingSource comenziBindingSource = new BindingSource();

        public void init()
        {
            curieriAdapter.SelectCommand = new SqlCommand($"SELECT * FROM Curieri", conn);
            comenziAdapter.SelectCommand = new SqlCommand($"SELECT * FROM Comenzi", conn);

            curieriAdapter.Fill(dataSet, "Curieri");
            comenziAdapter.Fill(dataSet, "Comenzi");

            DataTable curieriTable = dataSet.Tables["Curieri"];
            DataTable comenziTable = dataSet.Tables["Comenzi"];
            DataColumn curieriPkCol = curieriTable.Columns["id"];
            DataColumn comenziFkCol = comenziTable.Columns["id_curier"];
            DataRelation relation = new DataRelation("MyRelation", curieriPkCol, comenziFkCol);
            dataSet.Relations.Add(relation);
            curieriBindingSource.DataSource = curieriTable;
            comenziBindingSource.DataSource = curieriBindingSource;
            comenziBindingSource.DataMember = "MyRelation";

            dataGridViewParinte.DataSource = curieriBindingSource;
            dataGridViewCopii.DataSource = comenziBindingSource;

            var builder = new SqlCommandBuilder(comenziAdapter);
            comenziAdapter.UpdateCommand = builder.GetUpdateCommand();
            //comenziAdapter.InsertCommand = builder.GetInsertCommand();
            //comenziAdapter.DeleteCommand = builder.GetDeleteCommand();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            conn = new SqlConnection("Server=DESKTOP-GAQSC82;Database=Catering;Integrated Security=true;");
            try
            {
                conn.Open();
                init();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            comenziAdapter.Update(dataSet, "Comenzi");

            // only for display refresh
            dataSet.Tables["Comenzi"].Clear();
            comenziAdapter.Fill(dataSet, "Comenzi");

            MessageBox.Show("Baza de date a fost actualizata!");
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (conn != null && conn.State != ConnectionState.Closed)
                conn.Close();
        }
    }
}
