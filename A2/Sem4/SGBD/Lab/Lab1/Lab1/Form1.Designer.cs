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
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.splitContainer2 = new System.Windows.Forms.SplitContainer();
            this.dataGridViewCategorii = new System.Windows.Forms.DataGridView();
            this.dataGridViewSporturi = new System.Windows.Forms.DataGridView();
            this.tabControl = new System.Windows.Forms.TabControl();
            this.tabPageCategorii = new System.Windows.Forms.TabPage();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.textBoxTraining = new System.Windows.Forms.TextBox();
            this.textBoxCardiacRemodeling = new System.Windows.Forms.TextBox();
            this.textBoxCardiacOutput = new System.Windows.Forms.TextBox();
            this.textBoxBloodPreasure = new System.Windows.Forms.TextBox();
            this.textBoxHeartRate = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.textBoxDenumireCategorie = new System.Windows.Forms.TextBox();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.buttonDeleteCategorii = new System.Windows.Forms.Button();
            this.buttonUpdateCategorii = new System.Windows.Forms.Button();
            this.buttonAddCategorie = new System.Windows.Forms.Button();
            this.tabPageSporturi = new System.Windows.Forms.TabPage();
            this.buttonDelete = new System.Windows.Forms.Button();
            this.buttonUpdate = new System.Windows.Forms.Button();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.textBoxDenumire = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).BeginInit();
            this.splitContainer2.Panel1.SuspendLayout();
            this.splitContainer2.Panel2.SuspendLayout();
            this.splitContainer2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCategorii)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSporturi)).BeginInit();
            this.tabControl.SuspendLayout();
            this.tabPageCategorii.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tabPageSporturi.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.splitContainer2);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.tabControl);
            this.splitContainer1.Size = new System.Drawing.Size(969, 479);
            this.splitContainer1.SplitterDistance = 558;
            this.splitContainer1.TabIndex = 0;
            // 
            // splitContainer2
            // 
            this.splitContainer2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer2.Location = new System.Drawing.Point(0, 0);
            this.splitContainer2.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.splitContainer2.Name = "splitContainer2";
            this.splitContainer2.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer2.Panel1
            // 
            this.splitContainer2.Panel1.Controls.Add(this.dataGridViewCategorii);
            // 
            // splitContainer2.Panel2
            // 
            this.splitContainer2.Panel2.Controls.Add(this.dataGridViewSporturi);
            this.splitContainer2.Size = new System.Drawing.Size(558, 479);
            this.splitContainer2.SplitterDistance = 222;
            this.splitContainer2.TabIndex = 0;
            // 
            // dataGridViewCategorii
            // 
            this.dataGridViewCategorii.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewCategorii.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewCategorii.EditMode = System.Windows.Forms.DataGridViewEditMode.EditProgrammatically;
            this.dataGridViewCategorii.Location = new System.Drawing.Point(0, 0);
            this.dataGridViewCategorii.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.dataGridViewCategorii.MultiSelect = false;
            this.dataGridViewCategorii.Name = "dataGridViewCategorii";
            this.dataGridViewCategorii.RowHeadersWidth = 51;
            this.dataGridViewCategorii.RowTemplate.Height = 29;
            this.dataGridViewCategorii.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewCategorii.Size = new System.Drawing.Size(558, 222);
            this.dataGridViewCategorii.TabIndex = 1;
            this.dataGridViewCategorii.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewCategorii_CellContentClick);
            this.dataGridViewCategorii.SelectionChanged += new System.EventHandler(this.dataGridViewCategorii_SelectionChanged);
            // 
            // dataGridViewSporturi
            // 
            this.dataGridViewSporturi.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewSporturi.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewSporturi.EditMode = System.Windows.Forms.DataGridViewEditMode.EditProgrammatically;
            this.dataGridViewSporturi.Location = new System.Drawing.Point(0, 0);
            this.dataGridViewSporturi.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.dataGridViewSporturi.MultiSelect = false;
            this.dataGridViewSporturi.Name = "dataGridViewSporturi";
            this.dataGridViewSporturi.RowHeadersWidth = 51;
            this.dataGridViewSporturi.RowTemplate.Height = 29;
            this.dataGridViewSporturi.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewSporturi.Size = new System.Drawing.Size(558, 253);
            this.dataGridViewSporturi.TabIndex = 0;
            this.dataGridViewSporturi.SelectionChanged += new System.EventHandler(this.dataGridViewSporturi_SelectionChanged);
            // 
            // tabControl
            // 
            this.tabControl.Controls.Add(this.tabPageCategorii);
            this.tabControl.Controls.Add(this.tabPageSporturi);
            this.tabControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl.Location = new System.Drawing.Point(0, 0);
            this.tabControl.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tabControl.Name = "tabControl";
            this.tabControl.SelectedIndex = 0;
            this.tabControl.Size = new System.Drawing.Size(407, 479);
            this.tabControl.TabIndex = 0;
            // 
            // tabPageCategorii
            // 
            this.tabPageCategorii.Controls.Add(this.tableLayoutPanel1);
            this.tabPageCategorii.Location = new System.Drawing.Point(4, 32);
            this.tabPageCategorii.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tabPageCategorii.Name = "tabPageCategorii";
            this.tabPageCategorii.Padding = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tabPageCategorii.Size = new System.Drawing.Size(399, 443);
            this.tabPageCategorii.TabIndex = 1;
            this.tabPageCategorii.Text = "Categorii";
            this.tabPageCategorii.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 66.66666F));
            this.tableLayoutPanel1.Controls.Add(this.textBoxTraining, 1, 6);
            this.tableLayoutPanel1.Controls.Add(this.textBoxCardiacRemodeling, 1, 5);
            this.tableLayoutPanel1.Controls.Add(this.textBoxCardiacOutput, 1, 4);
            this.tableLayoutPanel1.Controls.Add(this.textBoxBloodPreasure, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.textBoxHeartRate, 1, 2);
            this.tableLayoutPanel1.Controls.Add(this.label13, 0, 6);
            this.tableLayoutPanel1.Controls.Add(this.label11, 0, 5);
            this.tableLayoutPanel1.Controls.Add(this.label9, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.label7, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.label5, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.label3, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.textBoxDenumireCategorie, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 0, 7);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(3, 4);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 8;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(393, 435);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // textBoxTraining
            // 
            this.textBoxTraining.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxTraining.Location = new System.Drawing.Point(134, 329);
            this.textBoxTraining.Name = "textBoxTraining";
            this.textBoxTraining.Size = new System.Drawing.Size(218, 30);
            this.textBoxTraining.TabIndex = 20;
            this.textBoxTraining.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBoxCardiacRemodeling
            // 
            this.textBoxCardiacRemodeling.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxCardiacRemodeling.Location = new System.Drawing.Point(134, 270);
            this.textBoxCardiacRemodeling.Name = "textBoxCardiacRemodeling";
            this.textBoxCardiacRemodeling.Size = new System.Drawing.Size(218, 30);
            this.textBoxCardiacRemodeling.TabIndex = 19;
            this.textBoxCardiacRemodeling.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBoxCardiacOutput
            // 
            this.textBoxCardiacOutput.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxCardiacOutput.Location = new System.Drawing.Point(134, 211);
            this.textBoxCardiacOutput.Name = "textBoxCardiacOutput";
            this.textBoxCardiacOutput.Size = new System.Drawing.Size(218, 30);
            this.textBoxCardiacOutput.TabIndex = 18;
            this.textBoxCardiacOutput.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBoxBloodPreasure
            // 
            this.textBoxBloodPreasure.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxBloodPreasure.Location = new System.Drawing.Point(134, 152);
            this.textBoxBloodPreasure.Name = "textBoxBloodPreasure";
            this.textBoxBloodPreasure.Size = new System.Drawing.Size(218, 30);
            this.textBoxBloodPreasure.TabIndex = 17;
            this.textBoxBloodPreasure.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBoxHeartRate
            // 
            this.textBoxHeartRate.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxHeartRate.Location = new System.Drawing.Point(134, 93);
            this.textBoxHeartRate.Name = "textBoxHeartRate";
            this.textBoxHeartRate.Size = new System.Drawing.Size(218, 30);
            this.textBoxHeartRate.TabIndex = 16;
            this.textBoxHeartRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // label13
            // 
            this.label13.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(28, 333);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(75, 23);
            this.label13.TabIndex = 13;
            this.label13.Text = "Training:";
            // 
            // label11
            // 
            this.label11.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(13, 262);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(105, 46);
            this.label11.TabIndex = 11;
            this.label11.Text = "Cardiac Remodeling:";
            this.label11.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label9
            // 
            this.label9.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(29, 203);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(72, 46);
            this.label9.TabIndex = 9;
            this.label9.Text = "Cardiac Output:";
            // 
            // label7
            // 
            this.label7.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(25, 144);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(80, 46);
            this.label7.TabIndex = 7;
            this.label7.Text = "Blood Preasure:";
            // 
            // label5
            // 
            this.label5.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(18, 97);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(95, 23);
            this.label5.TabIndex = 5;
            this.label5.Text = "Heart Rate:";
            // 
            // label3
            // 
            this.label3.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(21, 38);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(89, 23);
            this.label3.TabIndex = 3;
            this.label3.Text = "Denumire:";
            // 
            // textBoxDenumireCategorie
            // 
            this.textBoxDenumireCategorie.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxDenumireCategorie.Location = new System.Drawing.Point(134, 34);
            this.textBoxDenumireCategorie.Name = "textBoxDenumireCategorie";
            this.textBoxDenumireCategorie.Size = new System.Drawing.Size(218, 30);
            this.textBoxDenumireCategorie.TabIndex = 15;
            this.textBoxDenumireCategorie.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 3;
            this.tableLayoutPanel1.SetColumnSpan(this.tableLayoutPanel2, 2);
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel2.Controls.Add(this.buttonDeleteCategorii, 2, 0);
            this.tableLayoutPanel2.Controls.Add(this.buttonUpdateCategorii, 1, 0);
            this.tableLayoutPanel2.Controls.Add(this.buttonAddCategorie, 0, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 374);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 1;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(393, 61);
            this.tableLayoutPanel2.TabIndex = 21;
            // 
            // buttonDeleteCategorii
            // 
            this.buttonDeleteCategorii.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonDeleteCategorii.Location = new System.Drawing.Point(275, 10);
            this.buttonDeleteCategorii.Name = "buttonDeleteCategorii";
            this.buttonDeleteCategorii.Size = new System.Drawing.Size(105, 40);
            this.buttonDeleteCategorii.TabIndex = 2;
            this.buttonDeleteCategorii.Text = "Delete";
            this.buttonDeleteCategorii.UseVisualStyleBackColor = true;
            // 
            // buttonUpdateCategorii
            // 
            this.buttonUpdateCategorii.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonUpdateCategorii.Location = new System.Drawing.Point(144, 10);
            this.buttonUpdateCategorii.Name = "buttonUpdateCategorii";
            this.buttonUpdateCategorii.Size = new System.Drawing.Size(105, 40);
            this.buttonUpdateCategorii.TabIndex = 1;
            this.buttonUpdateCategorii.Text = "Update";
            this.buttonUpdateCategorii.UseVisualStyleBackColor = true;
            // 
            // buttonAddCategorie
            // 
            this.buttonAddCategorie.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonAddCategorie.Location = new System.Drawing.Point(13, 10);
            this.buttonAddCategorie.Name = "buttonAddCategorie";
            this.buttonAddCategorie.Size = new System.Drawing.Size(105, 40);
            this.buttonAddCategorie.TabIndex = 0;
            this.buttonAddCategorie.Text = "Add";
            this.buttonAddCategorie.UseVisualStyleBackColor = true;
            // 
            // tabPageSporturi
            // 
            this.tabPageSporturi.Controls.Add(this.buttonDelete);
            this.tabPageSporturi.Controls.Add(this.buttonUpdate);
            this.tabPageSporturi.Controls.Add(this.buttonAdd);
            this.tabPageSporturi.Controls.Add(this.textBoxDenumire);
            this.tabPageSporturi.Controls.Add(this.label1);
            this.tabPageSporturi.Location = new System.Drawing.Point(4, 29);
            this.tabPageSporturi.Name = "tabPageSporturi";
            this.tabPageSporturi.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageSporturi.Size = new System.Drawing.Size(399, 446);
            this.tabPageSporturi.TabIndex = 2;
            this.tabPageSporturi.Text = "Sporturi";
            this.tabPageSporturi.UseVisualStyleBackColor = true;
            // 
            // buttonDelete
            // 
            this.buttonDelete.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonDelete.Location = new System.Drawing.Point(271, 108);
            this.buttonDelete.Name = "buttonDelete";
            this.buttonDelete.Size = new System.Drawing.Size(105, 40);
            this.buttonDelete.TabIndex = 4;
            this.buttonDelete.Text = "Delete";
            this.buttonDelete.UseVisualStyleBackColor = true;
            this.buttonDelete.Click += new System.EventHandler(this.buttonDelete_Click);
            // 
            // buttonUpdate
            // 
            this.buttonUpdate.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonUpdate.Location = new System.Drawing.Point(151, 108);
            this.buttonUpdate.Name = "buttonUpdate";
            this.buttonUpdate.Size = new System.Drawing.Size(105, 40);
            this.buttonUpdate.TabIndex = 3;
            this.buttonUpdate.Text = "Update";
            this.buttonUpdate.UseVisualStyleBackColor = true;
            this.buttonUpdate.Click += new System.EventHandler(this.buttonUpdate_Click);
            // 
            // buttonAdd
            // 
            this.buttonAdd.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonAdd.Location = new System.Drawing.Point(22, 108);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(105, 40);
            this.buttonAdd.TabIndex = 2;
            this.buttonAdd.Text = "Add";
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // textBoxDenumire
            // 
            this.textBoxDenumire.Location = new System.Drawing.Point(32, 72);
            this.textBoxDenumire.Name = "textBoxDenumire";
            this.textBoxDenumire.Size = new System.Drawing.Size(213, 30);
            this.textBoxDenumire.TabIndex = 1;
            this.textBoxDenumire.TextChanged += new System.EventHandler(this.textBoxDenumire_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(32, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 23);
            this.label1.TabIndex = 0;
            this.label1.Text = "Denumire";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 23F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(969, 479);
            this.Controls.Add(this.splitContainer1);
            this.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.Text = "lab1";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.splitContainer2.Panel1.ResumeLayout(false);
            this.splitContainer2.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).EndInit();
            this.splitContainer2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCategorii)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSporturi)).EndInit();
            this.tabControl.ResumeLayout(false);
            this.tabPageCategorii.ResumeLayout(false);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tabPageSporturi.ResumeLayout(false);
            this.tabPageSporturi.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private SplitContainer splitContainer1;
        private SplitContainer splitContainer2;
        private DataGridView dataGridViewSporturi;
        private DataGridView dataGridViewCategorii;
        private TabControl tabControl;
        private TabPage tabPageCategorii;
        private TableLayoutPanel tableLayoutPanel1;
        private Label label13;
        private Label label11;
        private Label label9;
        private Label label7;
        private Label label5;
        private TextBox textBoxTraining;
        private TextBox textBoxCardiacRemodeling;
        private TextBox textBoxCardiacOutput;
        private TextBox textBoxBloodPreasure;
        private TextBox textBoxHeartRate;
        private Label label3;
        private TextBox textBoxDenumireCategorie;
        private TableLayoutPanel tableLayoutPanel2;
        private Button buttonDeleteCategorii;
        private Button buttonUpdateCategorii;
        private Button buttonAddCategorie;
        private TabPage tabPageSporturi;
        private Button buttonDelete;
        private Button buttonUpdate;
        private Button buttonAdd;
        private TextBox textBoxDenumire;
        private Label label1;
    }
}