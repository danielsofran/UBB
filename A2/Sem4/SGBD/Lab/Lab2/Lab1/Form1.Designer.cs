namespace Lab1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.labelChild = new System.Windows.Forms.Label();
            this.dataGridViewParent = new System.Windows.Forms.DataGridView();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.buttonRemove = new System.Windows.Forms.Button();
            this.dataGridViewChild = new System.Windows.Forms.DataGridView();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.labelParent = new System.Windows.Forms.Label();
            this.toolTipAdd = new System.Windows.Forms.ToolTip(this.components);
            this.toolTipRemove = new System.Windows.Forms.ToolTip(this.components);
            this.toolTipUpdate = new System.Windows.Forms.ToolTip(this.components);
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).BeginInit();
            this.tableLayoutPanel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.labelChild, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.dataGridViewParent, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelParent, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(969, 479);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelChild
            // 
            this.labelChild.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelChild.AutoSize = true;
            this.labelChild.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelChild.Location = new System.Drawing.Point(694, 11);
            this.labelChild.Name = "labelChild";
            this.labelChild.Size = new System.Drawing.Size(65, 28);
            this.labelChild.TabIndex = 10;
            this.labelChild.Text = "label2";
            this.toolTipUpdate.SetToolTip(this.labelChild, "Selecteaza o celula si modifica valoarea. Datele se vor actualiza automat.");
            // 
            // dataGridViewParent
            // 
            this.dataGridViewParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewParent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewParent.Location = new System.Drawing.Point(3, 53);
            this.dataGridViewParent.Name = "dataGridViewParent";
            this.dataGridViewParent.RowHeadersWidth = 51;
            this.dataGridViewParent.RowTemplate.Height = 29;
            this.dataGridViewParent.Size = new System.Drawing.Size(478, 423);
            this.dataGridViewParent.TabIndex = 9;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 1;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Controls.Add(this.buttonRemove, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.dataGridViewChild, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.buttonAdd, 0, 1);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(487, 53);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 3;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(479, 423);
            this.tableLayoutPanel2.TabIndex = 7;
            // 
            // buttonRemove
            // 
            this.buttonRemove.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonRemove.Location = new System.Drawing.Point(153, 380);
            this.buttonRemove.Name = "buttonRemove";
            this.buttonRemove.Size = new System.Drawing.Size(172, 35);
            this.buttonRemove.TabIndex = 4;
            this.buttonRemove.Text = "Remove";
            this.toolTipRemove.SetToolTip(this.buttonRemove, "Selecteaza Linia, nu o celula din cel de-al doilea tabel si apasa butonul Remove." +
        "");
            this.buttonRemove.UseVisualStyleBackColor = true;
            this.buttonRemove.Click += new System.EventHandler(this.buttonRemove_Click);
            // 
            // dataGridViewChild
            // 
            this.dataGridViewChild.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewChild.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewChild.Location = new System.Drawing.Point(3, 3);
            this.dataGridViewChild.Name = "dataGridViewChild";
            this.dataGridViewChild.RowHeadersWidth = 51;
            this.dataGridViewChild.RowTemplate.Height = 29;
            this.dataGridViewChild.Size = new System.Drawing.Size(473, 317);
            this.dataGridViewChild.TabIndex = 2;
            this.toolTipUpdate.SetToolTip(this.dataGridViewChild, "Selecteaza o celula si modifica valoarea. Datele se vor actualiza automat.");
            this.dataGridViewChild.CellEndEdit += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewChild_CellEndEdit);
            // 
            // buttonAdd
            // 
            this.buttonAdd.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonAdd.Location = new System.Drawing.Point(153, 330);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(172, 35);
            this.buttonAdd.TabIndex = 3;
            this.buttonAdd.Text = "Add";
            this.toolTipAdd.SetToolTip(this.buttonAdd, "Adauga datele pe ultima linie a tabelului din dreapta si apasa Add.");
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // labelParent
            // 
            this.labelParent.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelParent.AutoSize = true;
            this.labelParent.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelParent.Location = new System.Drawing.Point(209, 11);
            this.labelParent.Name = "labelParent";
            this.labelParent.Size = new System.Drawing.Size(65, 28);
            this.labelParent.TabIndex = 4;
            this.labelParent.Text = "label1";
            // 
            // toolTipAdd
            // 
            this.toolTipAdd.AutoPopDelay = 5000;
            this.toolTipAdd.InitialDelay = 1000;
            this.toolTipAdd.IsBalloon = true;
            this.toolTipAdd.ReshowDelay = 100;
            this.toolTipAdd.ToolTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            this.toolTipAdd.ToolTipTitle = "Adaugare";
            // 
            // toolTipRemove
            // 
            this.toolTipRemove.AutoPopDelay = 5000;
            this.toolTipRemove.InitialDelay = 1000;
            this.toolTipRemove.IsBalloon = true;
            this.toolTipRemove.ReshowDelay = 100;
            this.toolTipRemove.ToolTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            this.toolTipRemove.ToolTipTitle = "Stergere";
            // 
            // toolTipUpdate
            // 
            this.toolTipUpdate.AutoPopDelay = 5000;
            this.toolTipUpdate.InitialDelay = 1000;
            this.toolTipUpdate.IsBalloon = true;
            this.toolTipUpdate.ReshowDelay = 100;
            this.toolTipUpdate.ToolTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            this.toolTipUpdate.ToolTipTitle = "Actualizare";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(969, 479);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.Text = "Table View";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).EndInit();
            this.tableLayoutPanel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private TableLayoutPanel tableLayoutPanel1;
        private Label labelParent;
        private Label labelChild;
        private DataGridView dataGridViewParent;
        private TableLayoutPanel tableLayoutPanel2;
        private Button buttonRemove;
        private DataGridView dataGridViewChild;
        private Button buttonAdd;
        private ToolTip toolTipAdd;
        private ToolTip toolTipRemove;
        private ToolTip toolTipUpdate;
    }
}