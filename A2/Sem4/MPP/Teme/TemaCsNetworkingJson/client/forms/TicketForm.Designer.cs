namespace bilete.client
{
    partial class TicketForm
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxNume = new System.Windows.Forms.TextBox();
            this.numericUpDownLocuri = new System.Windows.Forms.NumericUpDown();
            this.buttonCumpara = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownLocuri)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(67, 72);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(184, 28);
            this.label1.TabIndex = 0;
            this.label1.Text = "Nume Cumparator: ";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(112, 133);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(139, 28);
            this.label2.TabIndex = 1;
            this.label2.Text = "Numar Locuri: ";
            // 
            // textBoxNume
            // 
            this.textBoxNume.Location = new System.Drawing.Point(257, 69);
            this.textBoxNume.Name = "textBoxNume";
            this.textBoxNume.Size = new System.Drawing.Size(187, 34);
            this.textBoxNume.TabIndex = 2;
            // 
            // numericUpDownLocuri
            // 
            this.numericUpDownLocuri.Location = new System.Drawing.Point(299, 131);
            this.numericUpDownLocuri.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.numericUpDownLocuri.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numericUpDownLocuri.Name = "numericUpDownLocuri";
            this.numericUpDownLocuri.Size = new System.Drawing.Size(93, 34);
            this.numericUpDownLocuri.TabIndex = 3;
            this.numericUpDownLocuri.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.numericUpDownLocuri.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // buttonCumpara
            // 
            this.buttonCumpara.Location = new System.Drawing.Point(189, 200);
            this.buttonCumpara.Name = "buttonCumpara";
            this.buttonCumpara.Size = new System.Drawing.Size(157, 45);
            this.buttonCumpara.TabIndex = 4;
            this.buttonCumpara.Text = "Cumpara";
            this.buttonCumpara.UseVisualStyleBackColor = true;
            this.buttonCumpara.Click += new System.EventHandler(this.buttonCumpara_Click);
            // 
            // TicketForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 28F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(546, 334);
            this.Controls.Add(this.buttonCumpara);
            this.Controls.Add(this.numericUpDownLocuri);
            this.Controls.Add(this.textBoxNume);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "TicketForm";
            this.Text = "TicketForm";
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownLocuri)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label1;
        private Label label2;
        private TextBox textBoxNume;
        private NumericUpDown numericUpDownLocuri;
        private Button buttonCumpara;
    }
}