namespace Lab8MAP.ui
{
    partial class MainFrom
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.labelMeci = new System.Windows.Forms.Label();
            this.button3 = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.labelScor = new System.Windows.Forms.Label();
            this.dateTimePickerTo = new System.Windows.Forms.DateTimePicker();
            this.label4 = new System.Windows.Forms.Label();
            this.dateTimePickerFrom = new System.Windows.Forms.DateTimePicker();
            this.label3 = new System.Windows.Forms.Label();
            this.comboBoxEchipa = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBoxJucatori = new System.Windows.Forms.GroupBox();
            this.listBoxJucatori = new System.Windows.Forms.ListBox();
            this.groupBoxMeciuri = new System.Windows.Forms.GroupBox();
            this.listBoxMeciuri = new System.Windows.Forms.ListBox();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.resetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tableLayoutPanel1.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBoxJucatori.SuspendLayout();
            this.groupBoxMeciuri.SuspendLayout();
            this.contextMenuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 40F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30F));
            this.tableLayoutPanel1.Controls.Add(this.groupBox3, 2, 0);
            this.tableLayoutPanel1.Controls.Add(this.groupBoxJucatori, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.groupBoxMeciuri, 1, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1441, 786);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.labelMeci);
            this.groupBox3.Controls.Add(this.button3);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.button2);
            this.groupBox3.Controls.Add(this.button1);
            this.groupBox3.Controls.Add(this.labelScor);
            this.groupBox3.Controls.Add(this.dateTimePickerTo);
            this.groupBox3.Controls.Add(this.label4);
            this.groupBox3.Controls.Add(this.dateTimePickerFrom);
            this.groupBox3.Controls.Add(this.label3);
            this.groupBox3.Controls.Add(this.comboBoxEchipa);
            this.groupBox3.Controls.Add(this.label2);
            this.groupBox3.Controls.Add(this.label1);
            this.groupBox3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox3.Location = new System.Drawing.Point(1011, 3);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(427, 780);
            this.groupBox3.TabIndex = 2;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Controls";
            // 
            // labelMeci
            // 
            this.labelMeci.AutoSize = true;
            this.labelMeci.Location = new System.Drawing.Point(25, 238);
            this.labelMeci.Name = "labelMeci";
            this.labelMeci.Size = new System.Drawing.Size(28, 38);
            this.labelMeci.TabIndex = 27;
            this.labelMeci.Text = "-";
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(25, 603);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(334, 84);
            this.button3.TabIndex = 26;
            this.button3.Text = "Meciurile din aceasta perioada";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Segoe UI", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label5.Location = new System.Drawing.Point(60, 706);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(119, 54);
            this.label5.TabIndex = 25;
            this.label5.Text = "Scor: ";
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(77, 414);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(230, 88);
            this.button2.TabIndex = 24;
            this.button2.Text = "Toti jucatorii activi de la meci";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(25, 141);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(312, 46);
            this.button1.TabIndex = 23;
            this.button1.Text = "Toti jucatorii echipei";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // labelScor
            // 
            this.labelScor.AutoSize = true;
            this.labelScor.Font = new System.Drawing.Font("Segoe UI", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelScor.Location = new System.Drawing.Point(185, 706);
            this.labelScor.Name = "labelScor";
            this.labelScor.Size = new System.Drawing.Size(0, 54);
            this.labelScor.TabIndex = 22;
            // 
            // dateTimePickerTo
            // 
            this.dateTimePickerTo.CustomFormat = " dd.MM.yyy";
            this.dateTimePickerTo.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateTimePickerTo.Location = new System.Drawing.Point(195, 554);
            this.dateTimePickerTo.Name = "dateTimePickerTo";
            this.dateTimePickerTo.Size = new System.Drawing.Size(164, 43);
            this.dateTimePickerTo.TabIndex = 21;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(195, 513);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(76, 38);
            this.label4.TabIndex = 20;
            this.label4.Text = "Pana";
            // 
            // dateTimePickerFrom
            // 
            this.dateTimePickerFrom.CustomFormat = " dd.MM.yyy";
            this.dateTimePickerFrom.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateTimePickerFrom.Location = new System.Drawing.Point(25, 554);
            this.dateTimePickerFrom.Name = "dateTimePickerFrom";
            this.dateTimePickerFrom.Size = new System.Drawing.Size(164, 43);
            this.dateTimePickerFrom.TabIndex = 19;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(25, 513);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(60, 38);
            this.label3.TabIndex = 18;
            this.label3.Text = "Din";
            // 
            // comboBoxEchipa
            // 
            this.comboBoxEchipa.FormattingEnabled = true;
            this.comboBoxEchipa.Location = new System.Drawing.Point(25, 80);
            this.comboBoxEchipa.Name = "comboBoxEchipa";
            this.comboBoxEchipa.Size = new System.Drawing.Size(312, 45);
            this.comboBoxEchipa.TabIndex = 16;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(25, 200);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(100, 38);
            this.label2.TabIndex = 15;
            this.label2.Text = "Meciul";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(25, 39);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(97, 38);
            this.label1.TabIndex = 14;
            this.label1.Text = "Echipa";
            // 
            // groupBoxJucatori
            // 
            this.groupBoxJucatori.Controls.Add(this.listBoxJucatori);
            this.groupBoxJucatori.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxJucatori.Location = new System.Drawing.Point(3, 3);
            this.groupBoxJucatori.Name = "groupBoxJucatori";
            this.groupBoxJucatori.Size = new System.Drawing.Size(426, 780);
            this.groupBoxJucatori.TabIndex = 0;
            this.groupBoxJucatori.TabStop = false;
            this.groupBoxJucatori.Text = "Jucatori";
            // 
            // listBoxJucatori
            // 
            this.listBoxJucatori.Dock = System.Windows.Forms.DockStyle.Fill;
            this.listBoxJucatori.FormattingEnabled = true;
            this.listBoxJucatori.ItemHeight = 37;
            this.listBoxJucatori.Location = new System.Drawing.Point(3, 39);
            this.listBoxJucatori.Name = "listBoxJucatori";
            this.listBoxJucatori.Size = new System.Drawing.Size(420, 738);
            this.listBoxJucatori.TabIndex = 0;
            // 
            // groupBoxMeciuri
            // 
            this.groupBoxMeciuri.Controls.Add(this.listBoxMeciuri);
            this.groupBoxMeciuri.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxMeciuri.Location = new System.Drawing.Point(435, 3);
            this.groupBoxMeciuri.Name = "groupBoxMeciuri";
            this.groupBoxMeciuri.Size = new System.Drawing.Size(570, 780);
            this.groupBoxMeciuri.TabIndex = 1;
            this.groupBoxMeciuri.TabStop = false;
            this.groupBoxMeciuri.Text = "Meciuri";
            // 
            // listBoxMeciuri
            // 
            this.listBoxMeciuri.ContextMenuStrip = this.contextMenuStrip1;
            this.listBoxMeciuri.Dock = System.Windows.Forms.DockStyle.Fill;
            this.listBoxMeciuri.FormattingEnabled = true;
            this.listBoxMeciuri.ItemHeight = 37;
            this.listBoxMeciuri.Location = new System.Drawing.Point(3, 39);
            this.listBoxMeciuri.Name = "listBoxMeciuri";
            this.listBoxMeciuri.Size = new System.Drawing.Size(564, 738);
            this.listBoxMeciuri.TabIndex = 1;
            this.listBoxMeciuri.SelectedIndexChanged += new System.EventHandler(this.listBoxMeciuri_SelectedIndexChanged);
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.resetToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(115, 28);
            // 
            // resetToolStripMenuItem
            // 
            this.resetToolStripMenuItem.Name = "resetToolStripMenuItem";
            this.resetToolStripMenuItem.Size = new System.Drawing.Size(114, 24);
            this.resetToolStripMenuItem.Text = "Reset";
            this.resetToolStripMenuItem.Click += new System.EventHandler(this.resetToolStripMenuItem_Click);
            // 
            // MainFrom
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(15F, 37F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1441, 786);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Font = new System.Drawing.Font("Segoe UI", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(6);
            this.Name = "MainFrom";
            this.Text = "MainFrom";
            this.Load += new System.EventHandler(this.MainFrom_Load);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBoxJucatori.ResumeLayout(false);
            this.groupBoxMeciuri.ResumeLayout(false);
            this.contextMenuStrip1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private TableLayoutPanel tableLayoutPanel1;
        private GroupBox groupBox3;
        private Label labelMeci;
        private Button button3;
        private Label label5;
        private Button button2;
        private Button button1;
        private Label labelScor;
        private DateTimePicker dateTimePickerTo;
        private Label label4;
        private DateTimePicker dateTimePickerFrom;
        private Label label3;
        private ComboBox comboBoxEchipa;
        private Label label2;
        private Label label1;
        private GroupBox groupBoxJucatori;
        private GroupBox groupBoxMeciuri;
        private ListBox listBoxJucatori;
        private ListBox listBoxMeciuri;
        private ContextMenuStrip contextMenuStrip1;
        private ToolStripMenuItem resetToolStripMenuItem;
    }
}