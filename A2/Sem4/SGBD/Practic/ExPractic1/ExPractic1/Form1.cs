using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace ExPractic1
{
    public partial class Form1 : Form, ParentChildTableApp
    {

        public Form1()
        {
            InitializeComponent();
        }
        
        private SqlConnection conn;
        private DataSet dataSet = new DataSet();
        private SqlDataAdapter parentAdapter = new SqlDataAdapter();
        private SqlDataAdapter childAdapter = new SqlDataAdapter();
        private BindingSource parentBindingSource = new BindingSource();
        private BindingSource childBindingSource = new BindingSource();

        public string ConnectionString => "Server=DESKTOP-GAQSC82;Database=Problema1;Integrated Security=true;";

        public string ParentTableName => "Cofetarii";

        public string ChildTableName => "Briose";

        public string ParentPKColName => "cod_cofetarie";

        public string ChildFKColName => "cod_cofetarie";

        public string ChildPKColName => "cod_briosa";

        public void init()
        {
            parentAdapter.SelectCommand = new SqlCommand($"SELECT * FROM {ParentTableName}", conn);
            childAdapter.SelectCommand = new SqlCommand($"SELECT * FROM {ChildTableName}", conn);

            parentAdapter.Fill(dataSet, ParentTableName);
            childAdapter.Fill(dataSet, ChildTableName);

            DataTable parentTable = dataSet.Tables[ParentTableName];
            DataTable childTable = dataSet.Tables[ChildTableName];
            DataColumn parentPkCol = parentTable.Columns[ParentPKColName];
            DataColumn childFkCol = childTable.Columns[ChildFKColName];
            DataRelation relation = new DataRelation("MyRelation", parentPkCol, childFkCol);
            dataSet.Relations.Add(relation);
            parentBindingSource.DataSource = parentTable;
            childBindingSource.DataSource = parentBindingSource;
            childBindingSource.DataMember = "MyRelation";

            dataGridViewParinte.DataSource = parentBindingSource;
            dataGridViewCopii.DataSource = childBindingSource;

            var builder = new SqlCommandBuilder(childAdapter);
            childAdapter.UpdateCommand = builder.GetUpdateCommand();
            //childAdapter.InsertCommand = builder.GetInsertCommand();
            //childAdapter.DeleteCommand = builder.GetDeleteCommand();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            conn = new SqlConnection(ConnectionString);
            try
            {
                conn.Open();
                init();
            }
            catch(Exception ex) {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            childAdapter.Update(dataSet, ChildTableName);

            // only for display refresh
            dataSet.Tables[ChildTableName].Clear();
            childAdapter.Fill(dataSet, ChildTableName);
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if(conn != null && conn.State != ConnectionState.Closed) 
                conn.Close();
        }
    }
}
