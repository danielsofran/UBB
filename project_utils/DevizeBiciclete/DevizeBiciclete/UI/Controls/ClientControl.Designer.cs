namespace DevizeBiciclete.UI.Controls
{
    partial class ClientControl
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.rdbtnjuridica = new System.Windows.Forms.RadioButton();
            this.rdbtnfizica = new System.Windows.Forms.RadioButton();
            this.textBoxTel = new System.Windows.Forms.TextBox();
            this.textBoxNume = new System.Windows.Forms.TextBox();
            this.textBoxDen = new System.Windows.Forms.TextBox();
            this.textBoxCIF = new System.Windows.Forms.TextBox();
            this.textBoxReg = new System.Windows.Forms.TextBox();
            this.textBoxAdr = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.textBoxTelF = new System.Windows.Forms.TextBox();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(81, 5);
            this.label1.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(87, 31);
            this.label1.TabIndex = 0;
            this.label1.Text = "Nume: ";
            this.toolTip1.SetToolTip(this.label1, "Nume și prenume");
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(388, 15);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(94, 41);
            this.label2.TabIndex = 1;
            this.label2.Text = "Client";
            // 
            // label3
            // 
            this.label3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(70, 48);
            this.label3.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(98, 31);
            this.label3.TabIndex = 2;
            this.label3.Text = "Telefon: ";
            this.toolTip1.SetToolTip(this.label3, "telefon persoana fizica");
            // 
            // label4
            // 
            this.label4.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(51, 92);
            this.label4.Margin = new System.Windows.Forms.Padding(5, 0, 5, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(117, 31);
            this.label4.TabIndex = 3;
            this.label4.Text = "Persoana: ";
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20.09724F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 79.90276F));
            this.tableLayoutPanel1.Controls.Add(this.label1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.label3, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.label5, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.label4, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.label6, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.label7, 0, 5);
            this.tableLayoutPanel1.Controls.Add(this.label8, 0, 6);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel3, 1, 2);
            this.tableLayoutPanel1.Controls.Add(this.textBoxTel, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.textBoxNume, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.textBoxDen, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.textBoxCIF, 1, 4);
            this.tableLayoutPanel1.Controls.Add(this.textBoxReg, 1, 5);
            this.tableLayoutPanel1.Controls.Add(this.textBoxAdr, 1, 6);
            this.tableLayoutPanel1.Controls.Add(this.label9, 0, 8);
            this.tableLayoutPanel1.Controls.Add(this.textBoxTelF, 1, 8);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(3, 74);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 9;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.11111F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(864, 391);
            this.tableLayoutPanel1.TabIndex = 4;
            // 
            // label5
            // 
            this.label5.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(43, 134);
            this.label5.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(125, 31);
            this.label5.TabIndex = 4;
            this.label5.Text = "Denumire: ";
            this.toolTip1.SetToolTip(this.label5, "pentru persoana juridica");
            // 
            // label6
            // 
            this.label6.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(102, 177);
            this.label6.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(66, 31);
            this.label6.TabIndex = 5;
            this.label6.Text = "C.I.F: ";
            // 
            // label7
            // 
            this.label7.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(10, 220);
            this.label7.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(158, 31);
            this.label7.TabIndex = 6;
            this.label7.Text = "Nr. Reg. Com: ";
            // 
            // label8
            // 
            this.label8.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(72, 263);
            this.label8.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(96, 31);
            this.label8.TabIndex = 7;
            this.label8.Text = "Adresa: ";
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 2;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.rdbtnjuridica, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.rdbtnfizica, 0, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(176, 89);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 1;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(685, 37);
            this.tableLayoutPanel3.TabIndex = 11;
            // 
            // rdbtnjuridica
            // 
            this.rdbtnjuridica.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.rdbtnjuridica.AutoSize = true;
            this.rdbtnjuridica.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.rdbtnjuridica.Location = new System.Drawing.Point(465, 3);
            this.rdbtnjuridica.Name = "rdbtnjuridica";
            this.rdbtnjuridica.Size = new System.Drawing.Size(97, 31);
            this.rdbtnjuridica.TabIndex = 1;
            this.rdbtnjuridica.Text = "juridică";
            this.rdbtnjuridica.UseVisualStyleBackColor = true;
            this.rdbtnjuridica.CheckedChanged += new System.EventHandler(this.radioButton2_CheckedChanged);
            // 
            // rdbtnfizica
            // 
            this.rdbtnfizica.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.rdbtnfizica.AutoSize = true;
            this.rdbtnfizica.Checked = true;
            this.rdbtnfizica.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.rdbtnfizica.Location = new System.Drawing.Point(132, 3);
            this.rdbtnfizica.Name = "rdbtnfizica";
            this.rdbtnfizica.Size = new System.Drawing.Size(77, 31);
            this.rdbtnfizica.TabIndex = 0;
            this.rdbtnfizica.TabStop = true;
            this.rdbtnfizica.Text = "fizica";
            this.rdbtnfizica.UseVisualStyleBackColor = true;
            this.rdbtnfizica.CheckedChanged += new System.EventHandler(this.radioButton2_CheckedChanged);
            // 
            // textBoxTel
            // 
            this.textBoxTel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxTel.Location = new System.Drawing.Point(176, 46);
            this.textBoxTel.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxTel.Name = "textBoxTel";
            this.textBoxTel.Size = new System.Drawing.Size(663, 38);
            this.textBoxTel.TabIndex = 10;
            this.textBoxTel.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.textBoxTel_KeyPress);
            // 
            // textBoxNume
            // 
            this.textBoxNume.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxNume.Location = new System.Drawing.Point(176, 3);
            this.textBoxNume.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxNume.Name = "textBoxNume";
            this.textBoxNume.Size = new System.Drawing.Size(663, 38);
            this.textBoxNume.TabIndex = 12;
            this.textBoxNume.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.textBoxNume_KeyPress);
            // 
            // textBoxDen
            // 
            this.textBoxDen.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxDen.Location = new System.Drawing.Point(176, 132);
            this.textBoxDen.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxDen.Name = "textBoxDen";
            this.textBoxDen.Size = new System.Drawing.Size(663, 38);
            this.textBoxDen.TabIndex = 13;
            this.textBoxDen.Tag = "juridica";
            // 
            // textBoxCIF
            // 
            this.textBoxCIF.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxCIF.Location = new System.Drawing.Point(176, 175);
            this.textBoxCIF.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxCIF.Name = "textBoxCIF";
            this.textBoxCIF.Size = new System.Drawing.Size(663, 38);
            this.textBoxCIF.TabIndex = 14;
            this.textBoxCIF.Tag = "juridica";
            // 
            // textBoxReg
            // 
            this.textBoxReg.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxReg.Location = new System.Drawing.Point(176, 218);
            this.textBoxReg.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxReg.Name = "textBoxReg";
            this.textBoxReg.Size = new System.Drawing.Size(663, 38);
            this.textBoxReg.TabIndex = 15;
            this.textBoxReg.Tag = "juridica";
            // 
            // textBoxAdr
            // 
            this.textBoxAdr.AcceptsReturn = true;
            this.textBoxAdr.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxAdr.Location = new System.Drawing.Point(176, 261);
            this.textBoxAdr.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxAdr.Multiline = true;
            this.textBoxAdr.Name = "textBoxAdr";
            this.tableLayoutPanel1.SetRowSpan(this.textBoxAdr, 2);
            this.textBoxAdr.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.textBoxAdr.Size = new System.Drawing.Size(663, 80);
            this.textBoxAdr.TabIndex = 17;
            this.textBoxAdr.Tag = "juridica";
            // 
            // label9
            // 
            this.label9.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(11, 349);
            this.label9.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(157, 31);
            this.label9.TabIndex = 8;
            this.label9.Text = "Telefon firma: ";
            // 
            // textBoxTelF
            // 
            this.textBoxTelF.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxTelF.Location = new System.Drawing.Point(176, 347);
            this.textBoxTelF.Margin = new System.Windows.Forms.Padding(3, 3, 25, 3);
            this.textBoxTelF.Name = "textBoxTelF";
            this.textBoxTelF.Size = new System.Drawing.Size(663, 38);
            this.textBoxTelF.TabIndex = 18;
            this.textBoxTelF.Tag = "juridica";
            this.textBoxTelF.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.textBoxTel_KeyPress);
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 1;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Controls.Add(this.label2, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.tableLayoutPanel1, 0, 1);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 2;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 15.36585F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 84.63415F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(870, 468);
            this.tableLayoutPanel2.TabIndex = 5;
            // 
            // toolTip1
            // 
            this.toolTip1.IsBalloon = true;
            this.toolTip1.ToolTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            // 
            // ClientControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 31F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel2);
            this.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "ClientControl";
            this.Size = new System.Drawing.Size(870, 468);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private TableLayoutPanel tableLayoutPanel1;
        private Label label5;
        private Label label6;
        private Label label7;
        private Label label8;
        private Label label9;
        private ToolTip toolTip1;
        private TableLayoutPanel tableLayoutPanel2;
        private TextBox textBoxTel;
        private TableLayoutPanel tableLayoutPanel3;
        private RadioButton rdbtnjuridica;
        private RadioButton rdbtnfizica;
        private TextBox textBoxNume;
        private TextBox textBoxDen;
        private TextBox textBoxCIF;
        private TextBox textBoxReg;
        private TextBox textBoxAdr;
        private TextBox textBoxTelF;
    }
}
