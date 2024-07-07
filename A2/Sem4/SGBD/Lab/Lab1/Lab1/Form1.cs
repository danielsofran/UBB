using System.Data;
using System.Data.SqlClient;

namespace Lab1
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection(Utils.ConnectionString);
        
        SqlDataAdapter categoriiAdapter = new SqlDataAdapter();
        SqlDataAdapter sporturiAdapter = new SqlDataAdapter();
        DataSet sporturiDataSet = new DataSet();

        int lastCategorieId;
        int lastSportId;

        public Form1()
        {
            InitializeComponent();
            tabControl.TabPages.Remove(tabPageCategorii);
        }

        void LoadCategorii()
        {
            categoriiAdapter.SelectCommand = new SqlCommand("SELECT * FROM Categorii", connection);
            if(sporturiDataSet.Tables.Contains("Categorii"))
                sporturiDataSet.Tables["Categorii"].Clear();
            categoriiAdapter.Fill(sporturiDataSet, "Categorii");
            dataGridViewCategorii.DataSource = sporturiDataSet.Tables["Categorii"];
        }

        void LoadSporturi(int categorieDI)
        {
            sporturiAdapter.SelectCommand = new SqlCommand("SELECT * FROM Sporturi WHERE categorie_id = @id", connection);
            sporturiAdapter.SelectCommand.Parameters.AddWithValue("@id", categorieDI);
            if (sporturiDataSet.Tables.Contains("Sporturi"))
                sporturiDataSet.Tables["Sporturi"].Clear();
            sporturiAdapter.Fill(sporturiDataSet, "Sporturi");
            dataGridViewSporturi.DataSource = sporturiDataSet.Tables["Sporturi"];
        }

        private void dataGridViewCategorii_SelectionChanged(object sender, EventArgs e)
        {
            if (dataGridViewCategorii.SelectedRows.Count > 0 && dataGridViewCategorii.SelectedRows[0].DataBoundItem != null)
            {
                lastCategorieId = (int)dataGridViewCategorii.SelectedRows[0].Cells["id"].Value;
                LoadSporturi(lastCategorieId);
            }
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            connection.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            connection.Open();
            LoadCategorii();
            textBoxDenumire_TextChanged(textBoxDenumire, null);
        }

        private void dataGridViewSporturi_SelectionChanged(object sender, EventArgs e)
        {
            if (dataGridViewSporturi.SelectedRows.Count > 0 && dataGridViewCategorii.SelectedRows[0].DataBoundItem != null)
            {
                lastSportId = (int)dataGridViewSporturi.SelectedRows[0].Cells["id"].Value;
                textBoxDenumire.Text = (string)dataGridViewSporturi.SelectedRows[0].Cells["denumire"].Value;
            }
        }

        private void textBoxDenumire_TextChanged(object sender, EventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (textBox.Text.Length == 0)
            {
                buttonAdd.Enabled = false;
                buttonUpdate.Enabled = false;
            }
            else
            {
                buttonAdd.Enabled = true;
                buttonUpdate.Enabled = true;
            }
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            string denumire = textBoxDenumire.Text;
            sporturiAdapter.InsertCommand = new SqlCommand(
                "INSERT INTO Sporturi(denumire, categorie_id) VALUES(@denumire, @categorie_id)", connection);
            sporturiAdapter.InsertCommand.Parameters.AddWithValue("@denumire", denumire);
            sporturiAdapter.InsertCommand.Parameters.AddWithValue("@categorie_id", lastCategorieId);
            sporturiAdapter.InsertCommand.ExecuteNonQuery();

            LoadSporturi(lastCategorieId);
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            string denumire = textBoxDenumire.Text;
            sporturiAdapter.UpdateCommand = new SqlCommand(
                "UPDATE Sporturi SET denumire=@denumire WHERE id=@id", connection);
            sporturiAdapter.UpdateCommand.Parameters.AddWithValue("@denumire", SqlDbType.NVarChar).Value = denumire;
            sporturiAdapter.UpdateCommand.Parameters.AddWithValue("@id", SqlDbType.Int).Value = lastSportId;
            sporturiAdapter.UpdateCommand.ExecuteNonQuery();

            LoadSporturi(lastCategorieId);
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
            sporturiAdapter.DeleteCommand = new SqlCommand(
                "DELETE FROM Sporturi WHERE id=@id", connection);
            sporturiAdapter.DeleteCommand.Parameters.AddWithValue("@id", lastSportId);
            sporturiAdapter.DeleteCommand.ExecuteNonQuery();

            LoadSporturi(lastCategorieId);
        }

        private void dataGridViewCategorii_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}