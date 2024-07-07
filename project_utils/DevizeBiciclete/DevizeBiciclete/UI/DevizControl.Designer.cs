namespace DevizeBiciclete.UI
{
    partial class DevizControl
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.deschideToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.modificareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.duplicaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.stergeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.exportPDFToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.textBoxTelefon = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxModel = new System.Windows.Forms.TextBox();
            this.numericUpDownPret = new System.Windows.Forms.NumericUpDown();
            this.textBoxNume = new System.Windows.Forms.TextBox();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.textBoxOut = new System.Windows.Forms.TextBox();
            this.textBoxIn = new System.Windows.Forms.TextBox();
            this.tableLayoutPanel1.SuspendLayout();
            this.contextMenuStrip1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPret)).BeginInit();
            this.tableLayoutPanel3.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 154F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.ContextMenuStrip = this.contextMenuStrip1;
            this.tableLayoutPanel1.Controls.Add(this.textBoxTelefon, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.label2, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.label1, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.label4, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.textBoxNume, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel3, 1, 2);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 4;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(825, 199);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.deschideToolStripMenuItem,
            this.modificareToolStripMenuItem,
            this.duplicaToolStripMenuItem,
            this.stergeToolStripMenuItem,
            this.exportPDFToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(152, 124);
            // 
            // deschideToolStripMenuItem
            // 
            this.deschideToolStripMenuItem.Name = "deschideToolStripMenuItem";
            this.deschideToolStripMenuItem.Size = new System.Drawing.Size(151, 24);
            this.deschideToolStripMenuItem.Text = "Vizualizare";
            this.deschideToolStripMenuItem.Click += new System.EventHandler(this.deschideToolStripMenuItem_Click);
            // 
            // modificareToolStripMenuItem
            // 
            this.modificareToolStripMenuItem.Name = "modificareToolStripMenuItem";
            this.modificareToolStripMenuItem.Size = new System.Drawing.Size(151, 24);
            this.modificareToolStripMenuItem.Text = "Modificare";
            this.modificareToolStripMenuItem.Click += new System.EventHandler(this.modificareToolStripMenuItem_Click);
            // 
            // duplicaToolStripMenuItem
            // 
            this.duplicaToolStripMenuItem.Name = "duplicaToolStripMenuItem";
            this.duplicaToolStripMenuItem.Size = new System.Drawing.Size(151, 24);
            this.duplicaToolStripMenuItem.Text = "Duplica";
            this.duplicaToolStripMenuItem.Click += new System.EventHandler(this.duplicaToolStripMenuItem_Click);
            // 
            // stergeToolStripMenuItem
            // 
            this.stergeToolStripMenuItem.Name = "stergeToolStripMenuItem";
            this.stergeToolStripMenuItem.Size = new System.Drawing.Size(151, 24);
            this.stergeToolStripMenuItem.Text = "Sterge";
            this.stergeToolStripMenuItem.Click += new System.EventHandler(this.stergeToolStripMenuItem_Click);
            // 
            // exportPDFToolStripMenuItem
            // 
            this.exportPDFToolStripMenuItem.Name = "exportPDFToolStripMenuItem";
            this.exportPDFToolStripMenuItem.Size = new System.Drawing.Size(151, 24);
            this.exportPDFToolStripMenuItem.Text = "Export PDF";
            this.exportPDFToolStripMenuItem.Click += new System.EventHandler(this.exportPDFToolStripMenuItem_Click);
            // 
            // textBoxTelefon
            // 
            this.textBoxTelefon.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxTelefon.Location = new System.Drawing.Point(157, 52);
            this.textBoxTelefon.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxTelefon.Name = "textBoxTelefon";
            this.textBoxTelefon.ReadOnly = true;
            this.textBoxTelefon.Size = new System.Drawing.Size(643, 38);
            this.textBoxTelefon.TabIndex = 12;
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(33, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(87, 31);
            this.label2.TabIndex = 6;
            this.label2.Text = "Nume: ";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label1
            // 
            this.label1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(31, 58);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(92, 31);
            this.label1.TabIndex = 7;
            this.label1.Text = "Telefon:";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label4
            // 
            this.label4.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label4.Location = new System.Drawing.Point(6, 107);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(141, 31);
            this.label4.TabIndex = 9;
            this.label4.Text = "Data in/out: ";
            this.label4.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 4;
            this.tableLayoutPanel1.SetColumnSpan(this.tableLayoutPanel2, 2);
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 18.75F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 18.75F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35F));
            this.tableLayoutPanel2.Controls.Add(this.label5, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.label6, 2, 0);
            this.tableLayoutPanel2.Controls.Add(this.textBoxModel, 3, 0);
            this.tableLayoutPanel2.Controls.Add(this.numericUpDownPret, 1, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 147);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 1;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(825, 52);
            this.tableLayoutPanel2.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label5.Location = new System.Drawing.Point(46, 10);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(66, 31);
            this.label5.TabIndex = 11;
            this.label5.Text = "Pret: ";
            this.label5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label6
            // 
            this.label6.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label6.Location = new System.Drawing.Point(402, 10);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(91, 31);
            this.label6.TabIndex = 12;
            this.label6.Text = "Model: ";
            this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // textBoxModel
            // 
            this.textBoxModel.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxModel.Location = new System.Drawing.Point(537, 8);
            this.textBoxModel.Margin = new System.Windows.Forms.Padding(10, 5, 25, 3);
            this.textBoxModel.Name = "textBoxModel";
            this.textBoxModel.ReadOnly = true;
            this.textBoxModel.Size = new System.Drawing.Size(263, 38);
            this.textBoxModel.TabIndex = 13;
            this.textBoxModel.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // numericUpDownPret
            // 
            this.numericUpDownPret.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.numericUpDownPret.DecimalPlaces = 2;
            this.numericUpDownPret.Enabled = false;
            this.numericUpDownPret.Location = new System.Drawing.Point(168, 8);
            this.numericUpDownPret.Margin = new System.Windows.Forms.Padding(10, 5, 3, 3);
            this.numericUpDownPret.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numericUpDownPret.Name = "numericUpDownPret";
            this.numericUpDownPret.ReadOnly = true;
            this.numericUpDownPret.Size = new System.Drawing.Size(131, 38);
            this.numericUpDownPret.TabIndex = 14;
            this.numericUpDownPret.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.numericUpDownPret.Value = new decimal(new int[] {
            10000,
            0,
            0,
            0});
            // 
            // textBoxNume
            // 
            this.textBoxNume.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxNume.Location = new System.Drawing.Point(157, 3);
            this.textBoxNume.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxNume.Name = "textBoxNume";
            this.textBoxNume.ReadOnly = true;
            this.textBoxNume.Size = new System.Drawing.Size(643, 38);
            this.textBoxNume.TabIndex = 11;
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 2;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.textBoxOut, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.textBoxIn, 0, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(157, 101);
            this.tableLayoutPanel3.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 1;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(643, 43);
            this.tableLayoutPanel3.TabIndex = 13;
            // 
            // textBoxOut
            // 
            this.textBoxOut.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBoxOut.Location = new System.Drawing.Point(388, 3);
            this.textBoxOut.Name = "textBoxOut";
            this.textBoxOut.ReadOnly = true;
            this.textBoxOut.Size = new System.Drawing.Size(188, 38);
            this.textBoxOut.TabIndex = 15;
            this.textBoxOut.Text = "13.13.2019";
            this.textBoxOut.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBoxIn
            // 
            this.textBoxIn.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBoxIn.Location = new System.Drawing.Point(66, 3);
            this.textBoxIn.Name = "textBoxIn";
            this.textBoxIn.ReadOnly = true;
            this.textBoxIn.Size = new System.Drawing.Size(188, 38);
            this.textBoxIn.TabIndex = 14;
            this.textBoxIn.Text = "12.33.2444";
            this.textBoxIn.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // DevizControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 31F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "DevizControl";
            this.Size = new System.Drawing.Size(825, 199);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.contextMenuStrip1.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPret)).EndInit();
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private TableLayoutPanel tableLayoutPanel1;
        private Label label2;
        private Label label1;
        private Label label4;
        private TableLayoutPanel tableLayoutPanel2;
        private Label label5;
        private Label label6;
        private TextBox textBoxTelefon;
        private TextBox textBoxNume;
        private TextBox textBoxModel;
        private TableLayoutPanel tableLayoutPanel3;
        private NumericUpDown numericUpDownPret;
        private TextBox textBoxOut;
        private TextBox textBoxIn;
        private ContextMenuStrip contextMenuStrip1;
        private ToolStripMenuItem deschideToolStripMenuItem;
        private ToolStripMenuItem modificareToolStripMenuItem;
        private ToolStripMenuItem stergeToolStripMenuItem;
        private ToolStripMenuItem exportPDFToolStripMenuItem;
        private ToolStripMenuItem duplicaToolStripMenuItem;
    }
}
