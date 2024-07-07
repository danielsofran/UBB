namespace DevizeBiciclete.UI
{
    partial class DevizForm
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
            DevizeBiciclete.Domain.DevizData.ClientData clientData1 = new DevizeBiciclete.Domain.DevizData.ClientData();
            DevizeBiciclete.Domain.DevizData.BicicletaData bicicletaData1 = new DevizeBiciclete.Domain.DevizData.BicicletaData();
            DevizeBiciclete.Domain.DevizData.ConstatareData constatareData1 = new DevizeBiciclete.Domain.DevizData.ConstatareData();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DevizForm));
            this.tableLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.label2 = new System.Windows.Forms.Label();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.numericUpDownTVA = new System.Windows.Forms.NumericUpDown();
            this.numericUpDownNumar = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.clientControl = new DevizeBiciclete.UI.Controls.ClientControl();
            this.bicicletaControl = new DevizeBiciclete.UI.Controls.BicicletaControl();
            this.constatareControl = new DevizeBiciclete.UI.Controls.ConstatareControl();
            this.piesaListControl = new DevizeBiciclete.UI.Controls.PiesaListControl();
            this.manoperaListControl = new DevizeBiciclete.UI.Controls.ManoperaListControl();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.button1 = new System.Windows.Forms.Button();
            this.buttonSalveaza = new System.Windows.Forms.Button();
            this.buttonAnuleaza = new System.Windows.Forms.Button();
            this.tableLayoutPanel.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownTVA)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownNumar)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel
            // 
            this.tableLayoutPanel.ColumnCount = 1;
            this.tableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel.Controls.Add(this.label2, 0, 0);
            this.tableLayoutPanel.Controls.Add(this.tableLayoutPanel3, 0, 1);
            this.tableLayoutPanel.Controls.Add(this.clientControl, 0, 2);
            this.tableLayoutPanel.Controls.Add(this.bicicletaControl, 0, 3);
            this.tableLayoutPanel.Controls.Add(this.constatareControl, 0, 4);
            this.tableLayoutPanel.Controls.Add(this.piesaListControl, 0, 5);
            this.tableLayoutPanel.Controls.Add(this.manoperaListControl, 0, 6);
            this.tableLayoutPanel.Controls.Add(this.tableLayoutPanel1, 0, 7);
            this.tableLayoutPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.tableLayoutPanel.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel.Name = "tableLayoutPanel";
            this.tableLayoutPanel.RowCount = 8;
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 122F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 55F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 466F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 269F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 228F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 330F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 335F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 8F));
            this.tableLayoutPanel.Size = new System.Drawing.Size(922, 1892);
            this.tableLayoutPanel.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(397, 34);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(127, 54);
            this.label2.TabIndex = 6;
            this.label2.Text = "Deviz";
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 4;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 161F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 153F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.numericUpDownTVA, 3, 0);
            this.tableLayoutPanel3.Controls.Add(this.numericUpDownNumar, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.label1, 0, 0);
            this.tableLayoutPanel3.Controls.Add(this.label3, 2, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(3, 125);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 1;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(916, 49);
            this.tableLayoutPanel3.TabIndex = 7;
            // 
            // numericUpDownTVA
            // 
            this.numericUpDownTVA.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.numericUpDownTVA.AutoSize = true;
            this.numericUpDownTVA.DecimalPlaces = 2;
            this.numericUpDownTVA.Location = new System.Drawing.Point(716, 5);
            this.numericUpDownTVA.Margin = new System.Windows.Forms.Padding(5);
            this.numericUpDownTVA.Name = "numericUpDownTVA";
            this.numericUpDownTVA.Size = new System.Drawing.Size(99, 38);
            this.numericUpDownTVA.TabIndex = 17;
            this.numericUpDownTVA.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // numericUpDownNumar
            // 
            this.numericUpDownNumar.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.numericUpDownNumar.AutoSize = true;
            this.numericUpDownNumar.Location = new System.Drawing.Point(166, 5);
            this.numericUpDownNumar.Margin = new System.Windows.Forms.Padding(5);
            this.numericUpDownNumar.Maximum = new decimal(new int[] {
            268435455,
            1042612833,
            542101086,
            0});
            this.numericUpDownNumar.Name = "numericUpDownNumar";
            this.numericUpDownNumar.Size = new System.Drawing.Size(291, 38);
            this.numericUpDownNumar.TabIndex = 16;
            this.numericUpDownNumar.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // label1
            // 
            this.label1.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(61, 11);
            this.label1.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(95, 31);
            this.label1.TabIndex = 2;
            this.label1.Text = "Numar: ";
            // 
            // label3
            // 
            this.label3.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(545, 11);
            this.label3.Margin = new System.Windows.Forms.Padding(5, 5, 5, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(65, 31);
            this.label3.TabIndex = 3;
            this.label3.Text = "TVA: ";
            // 
            // clientControl
            // 
            clientData1.Adresa = "";
            clientData1.Denumire = "";
            clientData1.Nume = "";
            clientData1.PersoanaFizica = true;
            clientData1.PersoanaJuridica = false;
            clientData1.Registru = "";
            clientData1.RO = "";
            clientData1.Telefon = "";
            clientData1.TelefonFrima = "";
            this.clientControl.Client = clientData1;
            this.clientControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.clientControl.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.clientControl.Location = new System.Drawing.Point(5, 202);
            this.clientControl.Margin = new System.Windows.Forms.Padding(5, 25, 5, 5);
            this.clientControl.Name = "clientControl";
            this.clientControl.Size = new System.Drawing.Size(912, 436);
            this.clientControl.TabIndex = 8;
            // 
            // bicicletaControl
            // 
            bicicletaData1.Culoare = "";
            bicicletaData1.Marca = "";
            bicicletaData1.Model = "";
            bicicletaData1.Serie = "";
            this.bicicletaControl.Bicicleta = bicicletaData1;
            this.bicicletaControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.bicicletaControl.Location = new System.Drawing.Point(5, 648);
            this.bicicletaControl.Margin = new System.Windows.Forms.Padding(5);
            this.bicicletaControl.Name = "bicicletaControl";
            this.bicicletaControl.Size = new System.Drawing.Size(912, 259);
            this.bicicletaControl.TabIndex = 9;
            // 
            // constatareControl
            // 
            constatareData1.DataIn = new System.DateTime(2022, 4, 21, 8, 40, 9, 539);
            constatareData1.DataOut = new System.DateTime(2022, 4, 21, 8, 40, 9, 541);
            constatareData1.Motiv = "";
            this.constatareControl.Constatare = constatareData1;
            this.constatareControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.constatareControl.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.constatareControl.Location = new System.Drawing.Point(5, 917);
            this.constatareControl.Margin = new System.Windows.Forms.Padding(5);
            this.constatareControl.Name = "constatareControl";
            this.constatareControl.Size = new System.Drawing.Size(912, 218);
            this.constatareControl.TabIndex = 10;
            // 
            // piesaListControl
            // 
            this.piesaListControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.piesaListControl.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.piesaListControl.Location = new System.Drawing.Point(5, 1145);
            this.piesaListControl.Margin = new System.Windows.Forms.Padding(5);
            this.piesaListControl.Name = "piesaListControl";
            this.piesaListControl.Size = new System.Drawing.Size(912, 320);
            this.piesaListControl.TabIndex = 11;
            this.piesaListControl.Resize += new System.EventHandler(this.manoperaListControl_Resize);
            // 
            // manoperaListControl
            // 
            this.manoperaListControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.manoperaListControl.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.manoperaListControl.Location = new System.Drawing.Point(5, 1475);
            this.manoperaListControl.Margin = new System.Windows.Forms.Padding(5);
            this.manoperaListControl.Name = "manoperaListControl";
            this.manoperaListControl.Size = new System.Drawing.Size(912, 325);
            this.manoperaListControl.TabIndex = 12;
            this.manoperaListControl.Resize += new System.EventHandler(this.manoperaListControl_Resize);
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.Controls.Add(this.button1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.buttonSalveaza, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.buttonAnuleaza, 2, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(3, 1808);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(916, 81);
            this.tableLayoutPanel1.TabIndex = 13;
            // 
            // button1
            // 
            this.button1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.button1.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.button1.Location = new System.Drawing.Point(332, 10);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(250, 60);
            this.button1.TabIndex = 3;
            this.button1.Text = "Export PDF";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // buttonSalveaza
            // 
            this.buttonSalveaza.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonSalveaza.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.buttonSalveaza.Location = new System.Drawing.Point(27, 10);
            this.buttonSalveaza.Name = "buttonSalveaza";
            this.buttonSalveaza.Size = new System.Drawing.Size(250, 60);
            this.buttonSalveaza.TabIndex = 1;
            this.buttonSalveaza.Text = "Salveaza";
            this.buttonSalveaza.UseVisualStyleBackColor = true;
            this.buttonSalveaza.Click += new System.EventHandler(this.button1_Click);
            // 
            // buttonAnuleaza
            // 
            this.buttonAnuleaza.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonAnuleaza.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.buttonAnuleaza.Location = new System.Drawing.Point(638, 10);
            this.buttonAnuleaza.Name = "buttonAnuleaza";
            this.buttonAnuleaza.Size = new System.Drawing.Size(250, 60);
            this.buttonAnuleaza.TabIndex = 2;
            this.buttonAnuleaza.Text = "Anuleaza";
            this.buttonAnuleaza.UseVisualStyleBackColor = true;
            this.buttonAnuleaza.Click += new System.EventHandler(this.button2_Click);
            // 
            // DevizForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 31F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.CancelButton = this.buttonAnuleaza;
            this.ClientSize = new System.Drawing.Size(943, 648);
            this.Controls.Add(this.tableLayoutPanel);
            this.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.MaximumSize = new System.Drawing.Size(1000000000, 1000000000);
            this.Name = "DevizForm";
            this.Text = "Deviz ";
            this.tableLayoutPanel.ResumeLayout(false);
            this.tableLayoutPanel.PerformLayout();
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownTVA)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownNumar)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private TableLayoutPanel tableLayoutPanel;
        private Label label2;
        private TableLayoutPanel tableLayoutPanel3;
        private NumericUpDown numericUpDownTVA;
        private NumericUpDown numericUpDownNumar;
        private Label label1;
        private Label label3;
        private Controls.ClientControl clientControl;
        private Controls.BicicletaControl bicicletaControl;
        private Controls.ConstatareControl constatareControl;
        private Controls.PiesaListControl piesaListControl;
        private Controls.ManoperaListControl manoperaListControl;
        private TableLayoutPanel tableLayoutPanel1;
        private Button buttonAnuleaza;
        private Button buttonSalveaza;
        private Button button1;
    }
}