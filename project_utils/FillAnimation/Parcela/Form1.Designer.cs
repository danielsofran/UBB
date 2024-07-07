namespace Parcela
{
    partial class Form1
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
            this.button1 = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.label3 = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.schimbaCuClickToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.alegeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem3 = new System.Windows.Forms.ToolStripMenuItem();
            this.coloreazaToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.schimbaCuloareaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ariaMaximaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.colorDialog1 = new System.Windows.Forms.ColorDialog();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.button4 = new System.Windows.Forms.Button();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.button3 = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.button5 = new System.Windows.Forms.Button();
            this.button6 = new System.Windows.Forms.Button();
            this.button7 = new System.Windows.Forms.Button();
            this.numericUpDown1 = new System.Windows.Forms.NumericUpDown();
            this.numericUpDown2 = new System.Windows.Forms.NumericUpDown();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.contextMenuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown2)).BeginInit();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(188, 55);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(175, 41);
            this.button1.TabIndex = 4;
            this.button1.Text = "Alege fișier";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Red;
            this.pictureBox1.Location = new System.Drawing.Point(530, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(38, 36);
            this.pictureBox1.TabIndex = 5;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.PictureBox1_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(407, 16);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(117, 31);
            this.label3.TabIndex = 6;
            this.label3.Text = "Culoare:";
            this.toolTip1.SetToolTip(this.label3, "Apăsați pătratul colorat pentru a schimba culoarea de fill.");
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(600, 12);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(144, 38);
            this.button2.TabIndex = 7;
            this.button2.Text = "Auto Fill";
            this.toolTip1.SetToolTip(this.button2, "Colorează automat matricea");
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.Button2_Click);
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.schimbaCuClickToolStripMenuItem,
            this.alegeToolStripMenuItem,
            this.coloreazaToolStripMenuItem1,
            this.schimbaCuloareaToolStripMenuItem,
            this.ariaMaximaToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(184, 114);
            // 
            // schimbaCuClickToolStripMenuItem
            // 
            this.schimbaCuClickToolStripMenuItem.Name = "schimbaCuClickToolStripMenuItem";
            this.schimbaCuClickToolStripMenuItem.Size = new System.Drawing.Size(183, 22);
            this.schimbaCuClickToolStripMenuItem.Text = "Schimba cu click";
            this.schimbaCuClickToolStripMenuItem.Click += new System.EventHandler(this.SchimbaCuClickToolStripMenuItem_Click);
            // 
            // alegeToolStripMenuItem
            // 
            this.alegeToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem2,
            this.toolStripMenuItem3});
            this.alegeToolStripMenuItem.Name = "alegeToolStripMenuItem";
            this.alegeToolStripMenuItem.Size = new System.Drawing.Size(183, 22);
            this.alegeToolStripMenuItem.Text = "Alege cifra pt mouse";
            // 
            // toolStripMenuItem2
            // 
            this.toolStripMenuItem2.Name = "toolStripMenuItem2";
            this.toolStripMenuItem2.Size = new System.Drawing.Size(80, 22);
            this.toolStripMenuItem2.Text = "1";
            this.toolStripMenuItem2.Click += new System.EventHandler(this.ToolStripMenuItem2_Click);
            // 
            // toolStripMenuItem3
            // 
            this.toolStripMenuItem3.Name = "toolStripMenuItem3";
            this.toolStripMenuItem3.Size = new System.Drawing.Size(80, 22);
            this.toolStripMenuItem3.Text = "0";
            this.toolStripMenuItem3.Click += new System.EventHandler(this.ToolStripMenuItem3_Click);
            // 
            // coloreazaToolStripMenuItem1
            // 
            this.coloreazaToolStripMenuItem1.Name = "coloreazaToolStripMenuItem1";
            this.coloreazaToolStripMenuItem1.Size = new System.Drawing.Size(183, 22);
            this.coloreazaToolStripMenuItem1.Text = "Coloreaza cu click";
            this.coloreazaToolStripMenuItem1.Click += new System.EventHandler(this.ColoreazaToolStripMenuItem1_Click);
            // 
            // schimbaCuloareaToolStripMenuItem
            // 
            this.schimbaCuloareaToolStripMenuItem.Name = "schimbaCuloareaToolStripMenuItem";
            this.schimbaCuloareaToolStripMenuItem.Size = new System.Drawing.Size(183, 22);
            this.schimbaCuloareaToolStripMenuItem.Text = "Schimba culoarea";
            this.schimbaCuloareaToolStripMenuItem.Click += new System.EventHandler(this.SchimbaCuloareaToolStripMenuItem_Click);
            // 
            // ariaMaximaToolStripMenuItem
            // 
            this.ariaMaximaToolStripMenuItem.Name = "ariaMaximaToolStripMenuItem";
            this.ariaMaximaToolStripMenuItem.Size = new System.Drawing.Size(183, 22);
            this.ariaMaximaToolStripMenuItem.Text = "Aria maxima";
            this.ariaMaximaToolStripMenuItem.Click += new System.EventHandler(this.AriaMaximaToolStripMenuItem_Click);
            // 
            // label1
            // 
            this.label1.AccessibleDescription = "";
            this.label1.AccessibleName = "";
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(838, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(74, 31);
            this.label1.TabIndex = 11;
            this.label1.Tag = "";
            this.label1.Text = "Mod:";
            this.toolTip1.SetToolTip(this.label1, "Modul de lucru se poate schimba apăsând click dreapta oriunde.");
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(918, 16);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(138, 31);
            this.label2.TabIndex = 12;
            this.label2.Text = "Colorează";
            this.toolTip1.SetToolTip(this.label2, "Modul de lucru se poate schimba apăsând click dreapta oriunde.");
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(600, 58);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(144, 38);
            this.button4.TabIndex = 13;
            this.button4.Text = "Refresh";
            this.toolTip1.SetToolTip(this.button4, "Șterge colorarea");
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.Button4_Click);
            // 
            // flowLayoutPanel1
            // 
            this.flowLayoutPanel1.AutoScroll = true;
            this.flowLayoutPanel1.Location = new System.Drawing.Point(29, 126);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(189, 82);
            this.flowLayoutPanel1.TabIndex = 14;
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            // 
            // toolTip1
            // 
            this.toolTip1.IsBalloon = true;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(22, 19);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(52, 31);
            this.label4.TabIndex = 17;
            this.label4.Text = "n =";
            this.toolTip1.SetToolTip(this.label4, "Numărul de linii");
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(15, 58);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(59, 31);
            this.label5.TabIndex = 18;
            this.label5.Text = "m =";
            this.toolTip1.SetToolTip(this.label5, "Numărul de coloane");
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(188, 12);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(175, 38);
            this.button3.TabIndex = 19;
            this.button3.Text = "Generează";
            this.toolTip1.SetToolTip(this.button3, "Folosind n și m");
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(814, 57);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(98, 31);
            this.label6.TabIndex = 20;
            this.label6.Text = "Viteză:";
            this.toolTip1.SetToolTip(this.label6, "Viteza animației");
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(380, 55);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(199, 41);
            this.button5.TabIndex = 22;
            this.button5.Text = "Culori Predef.";
            this.toolTip1.SetToolTip(this.button5, "Culorile cu care se face Auto Fill-ul");
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(1131, 12);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(175, 38);
            this.button6.TabIndex = 23;
            this.button6.Text = "Problemă";
            this.toolTip1.SetToolTip(this.button6, "Textul problemei");
            this.button6.UseVisualStyleBackColor = true;
            this.button6.Click += new System.EventHandler(this.button6_Click);
            // 
            // button7
            // 
            this.button7.Location = new System.Drawing.Point(1131, 56);
            this.button7.Name = "button7";
            this.button7.Size = new System.Drawing.Size(175, 38);
            this.button7.TabIndex = 24;
            this.button7.Text = "Cod";
            this.toolTip1.SetToolTip(this.button7, "Rezolvarea c++");
            this.button7.UseVisualStyleBackColor = true;
            this.button7.Click += new System.EventHandler(this.button7_Click);
            // 
            // numericUpDown1
            // 
            this.numericUpDown1.Location = new System.Drawing.Point(80, 14);
            this.numericUpDown1.Maximum = new decimal(new int[] {
            32,
            0,
            0,
            0});
            this.numericUpDown1.Name = "numericUpDown1";
            this.numericUpDown1.Size = new System.Drawing.Size(61, 38);
            this.numericUpDown1.TabIndex = 15;
            // 
            // numericUpDown2
            // 
            this.numericUpDown2.Location = new System.Drawing.Point(80, 58);
            this.numericUpDown2.Maximum = new decimal(new int[] {
            32,
            0,
            0,
            0});
            this.numericUpDown2.Name = "numericUpDown2";
            this.numericUpDown2.Size = new System.Drawing.Size(61, 38);
            this.numericUpDown2.TabIndex = 16;
            // 
            // comboBox1
            // 
            this.comboBox1.DisplayMember = "Mare";
            this.comboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "Mică",
            "Medie",
            "Mare",
            "Extra",
            "Instant"});
            this.comboBox1.Location = new System.Drawing.Point(918, 54);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(138, 39);
            this.comboBox1.TabIndex = 21;
            this.comboBox1.ValueMember = "Mare";
            this.comboBox1.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(16F, 31F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1393, 608);
            this.ContextMenuStrip = this.contextMenuStrip1;
            this.Controls.Add(this.button7);
            this.Controls.Add(this.button6);
            this.Controls.Add(this.button5);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.numericUpDown2);
            this.Controls.Add(this.numericUpDown1);
            this.Controls.Add(this.flowLayoutPanel1);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.button1);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.Margin = new System.Windows.Forms.Padding(8, 7, 8, 7);
            this.Name = "Form1";
            this.Text = "Parcela";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.Resize += new System.EventHandler(this.Form1_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.contextMenuStrip1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem alegeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem2;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem3;
        private System.Windows.Forms.ToolStripMenuItem coloreazaToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem schimbaCuClickToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem schimbaCuloareaToolStripMenuItem;
        private System.Windows.Forms.ColorDialog colorDialog1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.ToolStripMenuItem ariaMaximaToolStripMenuItem;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.NumericUpDown numericUpDown1;
        private System.Windows.Forms.NumericUpDown numericUpDown2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button button7;
    }
}

