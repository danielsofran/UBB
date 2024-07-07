using System;
using System.Data;
using System.Data.SqlClient;
using System.Text;
using System.Windows.Forms;

namespace Lab1
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection(Utils.ConnectionString);
        DataSet dataSet = new DataSet();

        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();

        BindingSource parentBS = new BindingSource();
        BindingSource childBS = new BindingSource();

        private string ParentTableName { get => Utils.Setting("ParentTable"); }
        private string ChildTableName { get => Utils.Setting("ChildTable"); }
        private string ChildPkColName { get => Utils.Setting("ChildPkCol"); }
        private string ParentPkColName { get => Utils.Setting("ParentPkCol"); }

        private List<string> fieldsChild = new List<string>();

        public Form1()
        {
            InitializeComponent();
            labelParent.Text = ParentTableName;
            labelChild.Text = ChildTableName;
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            connection.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                connection.Open();
                // SELECT
                parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM " + ParentTableName, connection);
                childAdapter.SelectCommand = new SqlCommand("SELECT * FROM " + ChildTableName, connection);
                
                // INSERT
                parentAdapter.Fill(dataSet, Utils.Setting("ParentTable"));
                childAdapter.Fill(dataSet, Utils.Setting("ChildTable"));
                
                DataTable parentTable = dataSet.Tables[ParentTableName];
                DataTable childTable = dataSet.Tables[ChildTableName];
                string fk = Utils.Setting("ParentFkCol"), pk = ChildPkColName;
                DataColumn parentPkCol = parentTable.Columns[ParentPkColName];
                DataColumn childPkCol = childTable.Columns[fk];
                DataRelation relation = new DataRelation(Utils.Setting("RelationName"), parentPkCol, childPkCol);
                dataSet.Relations.Add(relation);
                parentBS.DataSource = parentTable;
                dataGridViewParent.DataSource = parentBS;
                childBS.DataSource = parentBS;
                childBS.DataMember = Utils.Setting("RelationName");
                dataGridViewChild.DataSource = childBS;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonRemove_Click(object sender, EventArgs e)
        {
            var toDelete = new List<int>();
            foreach (DataGridViewRow item in this.dataGridViewChild.SelectedRows)
                if (item.Index != dataGridViewChild.RowCount -1 &&
                    MessageBox.Show($"Doriti sa stergeti elementul selectat, de la linia {item.Index + 1}?", "Atentie", MessageBoxButtons.OKCancel) == DialogResult.OK)
                {
                    childAdapter.DeleteCommand = new SqlCommand($"DELETE FROM {ChildTableName} WHERE {ChildPkColName} = {item.Cells[ChildPkColName].Value}", connection);
                    childAdapter.DeleteCommand.ExecuteNonQuery();
                    toDelete.Add(item.Index);
                }
            foreach(var index in toDelete)
                dataGridViewChild.Rows.RemoveAt(index);
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            SqlCommand command = new SqlCommand() { Connection = connection};
            string fieldNames = "", paramNames = "";
            foreach (DataGridViewColumn col in dataGridViewChild.Columns)
                if(col.HeaderText != ChildPkColName)
                {
                    string param = "@" + col.HeaderText;
                    fieldNames += col.HeaderText + ",";
                    paramNames += param + ",";
                    var val = dataGridViewChild.Rows[dataGridViewChild.Rows.Count - 2].Cells[col.Index].Value;
                    command.Parameters.AddWithValue(param, val);
                }
            fieldNames = fieldNames.Remove(fieldNames.Length - 1);
            paramNames = paramNames.Remove(paramNames.Length - 1);
            command.CommandText = $"INSERT INTO {ChildTableName} ({fieldNames}) VALUES ({paramNames})";
            childAdapter.InsertCommand = command;
            childAdapter.InsertCommand.ExecuteNonQuery();

            dataSet.Tables[ChildTableName].Clear();
            childAdapter.Fill(dataSet, ChildTableName);
        }

        private void dataGridViewChild_CellEndEdit(object sender, DataGridViewCellEventArgs e)
        {
            int row = e.RowIndex;
            int col = e.ColumnIndex;
            if (row == dataGridViewChild.RowCount - 1 ||
                dataGridViewChild.Columns[ChildPkColName].Index == col) return;
            string columnName = dataGridViewChild.Columns[e.ColumnIndex].HeaderText;
            string value = dataGridViewChild.Rows[row].Cells[col].Value.ToString();
            int primaryKeyValue;
            try
            {
                primaryKeyValue = (int)dataGridViewChild.Rows[row].Cells[ChildPkColName].Value;
            }
            catch { return; }
            childAdapter.UpdateCommand = new SqlCommand($"UPDATE {ChildTableName} SET {columnName}=@value WHERE {ChildPkColName}=@primaryKeyValue", connection);
            childAdapter.UpdateCommand.Parameters.AddWithValue("@value", value);
            childAdapter.UpdateCommand.Parameters.AddWithValue("@primaryKeyValue", primaryKeyValue);
            childAdapter.UpdateCommand.ExecuteNonQuery();
        }
    }
}