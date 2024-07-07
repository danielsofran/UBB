namespace TemaCS.controller
{
    partial class MainForm
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
            this.listViewArtisti = new System.Windows.Forms.ListView();
            this.Artist = new System.Windows.Forms.ColumnHeader();
            this.Date = new System.Windows.Forms.ColumnHeader();
            this.Location = new System.Windows.Forms.ColumnHeader();
            this.AvailableSeats = new System.Windows.Forms.ColumnHeader();
            this.SoldSeats = new System.Windows.Forms.ColumnHeader();
            this.tableLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.listViewSpectacole = new System.Windows.Forms.ListView();
            this.columnHeader1 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader3 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader4 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader5 = new System.Windows.Forms.ColumnHeader();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.buttonLogout = new System.Windows.Forms.Button();
            this.buttonCauta = new System.Windows.Forms.Button();
            this.dateTimePicker = new System.Windows.Forms.DateTimePicker();
            this.buttonCumpara = new System.Windows.Forms.Button();
            this.tableLayoutPanel.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // listViewArtisti
            // 
            this.listViewArtisti.AllowColumnReorder = true;
            this.listViewArtisti.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Artist,
            this.Date,
            this.Location,
            this.AvailableSeats,
            this.SoldSeats});
            this.listViewArtisti.Dock = System.Windows.Forms.DockStyle.Fill;
            this.listViewArtisti.Location = new System.Drawing.Point(4, 74);
            this.listViewArtisti.Margin = new System.Windows.Forms.Padding(4);
            this.listViewArtisti.Name = "listViewArtisti";
            this.listViewArtisti.Size = new System.Drawing.Size(1122, 219);
            this.listViewArtisti.TabIndex = 0;
            this.listViewArtisti.UseCompatibleStateImageBehavior = false;
            this.listViewArtisti.View = System.Windows.Forms.View.Details;
            // 
            // Artist
            // 
            this.Artist.Text = "Nume Artist";
            this.Artist.Width = 130;
            // 
            // Date
            // 
            this.Date.Text = "Data";
            this.Date.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.Date.Width = 120;
            // 
            // Location
            // 
            this.Location.Text = "Locul Spectacolului";
            this.Location.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.Location.Width = 200;
            // 
            // AvailableSeats
            // 
            this.AvailableSeats.Text = "Nr Locuri Disponibile";
            this.AvailableSeats.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.AvailableSeats.Width = 200;
            // 
            // SoldSeats
            // 
            this.SoldSeats.Text = "Nr Locuri Ocupate";
            this.SoldSeats.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.SoldSeats.Width = 200;
            // 
            // tableLayoutPanel
            // 
            this.tableLayoutPanel.ColumnCount = 1;
            this.tableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel.Controls.Add(this.listViewSpectacole, 0, 2);
            this.tableLayoutPanel.Controls.Add(this.listViewArtisti, 0, 1);
            this.tableLayoutPanel.Controls.Add(this.tableLayoutPanel1, 0, 0);
            this.tableLayoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel.Name = "tableLayoutPanel";
            this.tableLayoutPanel.RowCount = 3;
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 70F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel.Size = new System.Drawing.Size(1130, 524);
            this.tableLayoutPanel.TabIndex = 1;
            // 
            // listViewSpectacole
            // 
            this.listViewSpectacole.AllowColumnReorder = true;
            this.listViewSpectacole.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader3,
            this.columnHeader4,
            this.columnHeader5});
            this.listViewSpectacole.Dock = System.Windows.Forms.DockStyle.Fill;
            this.listViewSpectacole.Location = new System.Drawing.Point(4, 301);
            this.listViewSpectacole.Margin = new System.Windows.Forms.Padding(4);
            this.listViewSpectacole.Name = "listViewSpectacole";
            this.listViewSpectacole.Size = new System.Drawing.Size(1122, 219);
            this.listViewSpectacole.TabIndex = 2;
            this.listViewSpectacole.UseCompatibleStateImageBehavior = false;
            this.listViewSpectacole.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Nume Artist";
            this.columnHeader1.Width = 130;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "Locul Spectacolului";
            this.columnHeader3.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.columnHeader3.Width = 200;
            // 
            // columnHeader4
            // 
            this.columnHeader4.Text = "Ora inceperii";
            this.columnHeader4.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.columnHeader4.Width = 150;
            // 
            // columnHeader5
            // 
            this.columnHeader5.Text = "Nr Locuri Disponibile";
            this.columnHeader5.Width = 200;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 4;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35.71429F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35.71429F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.Controls.Add(this.buttonLogout, 3, 0);
            this.tableLayoutPanel1.Controls.Add(this.buttonCauta, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.dateTimePicker, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.buttonCumpara, 2, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1130, 70);
            this.tableLayoutPanel1.TabIndex = 1;
            // 
            // buttonLogout
            // 
            this.buttonLogout.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonLogout.Location = new System.Drawing.Point(990, 10);
            this.buttonLogout.Name = "buttonLogout";
            this.buttonLogout.Size = new System.Drawing.Size(116, 49);
            this.buttonLogout.TabIndex = 4;
            this.buttonLogout.Text = "Logout";
            this.buttonLogout.UseVisualStyleBackColor = true;
            this.buttonLogout.Click += new System.EventHandler(this.buttonLogout_Click);
            // 
            // buttonCauta
            // 
            this.buttonCauta.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonCauta.Location = new System.Drawing.Point(425, 10);
            this.buttonCauta.Name = "buttonCauta";
            this.buttonCauta.Size = new System.Drawing.Size(116, 49);
            this.buttonCauta.TabIndex = 1;
            this.buttonCauta.Text = "Cauta";
            this.buttonCauta.UseVisualStyleBackColor = true;
            this.buttonCauta.Click += new System.EventHandler(this.buttonCauta_Click);
            // 
            // dateTimePicker
            // 
            this.dateTimePicker.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.dateTimePicker.CustomFormat = "dd.MM.yyyy";
            this.dateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateTimePicker.Location = new System.Drawing.Point(127, 18);
            this.dateTimePicker.Name = "dateTimePicker";
            this.dateTimePicker.Size = new System.Drawing.Size(148, 34);
            this.dateTimePicker.TabIndex = 2;
            this.dateTimePicker.Value = new System.DateTime(2023, 3, 23, 0, 0, 0, 0);
            // 
            // buttonCumpara
            // 
            this.buttonCumpara.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.buttonCumpara.Location = new System.Drawing.Point(584, 12);
            this.buttonCumpara.Name = "buttonCumpara";
            this.buttonCumpara.Size = new System.Drawing.Size(363, 45);
            this.buttonCumpara.TabIndex = 3;
            this.buttonCumpara.Text = "Cumpara bilet la spectacolul selectat";
            this.buttonCumpara.UseVisualStyleBackColor = true;
            this.buttonCumpara.Click += new System.EventHandler(this.buttonCumpara_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 28F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1130, 524);
            this.Controls.Add(this.tableLayoutPanel);
            this.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "MainForm";
            this.Text = "MainForm";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.MainForm_FormClosed);
            this.Shown += new System.EventHandler(this.MainForm_Load);
            this.tableLayoutPanel.ResumeLayout(false);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private ListView listViewArtisti;
        private ColumnHeader Artist;
        private ColumnHeader Date;
        private ColumnHeader Location;
        private ColumnHeader AvailableSeats;
        private ColumnHeader SoldSeats;
        private TableLayoutPanel tableLayoutPanel;
        private TableLayoutPanel tableLayoutPanel1;
        private Button buttonLogout;
        private Button buttonCauta;
        private DateTimePicker dateTimePicker;
        private Button buttonCumpara;
        private ListView listViewSpectacole;
        private ColumnHeader columnHeader1;
        private ColumnHeader columnHeader3;
        private ColumnHeader columnHeader4;
        private ColumnHeader columnHeader5;
    }
}