namespace S12.comps
{
    partial class UserControlAngajat
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
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.labelNume = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.labelVenit = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.labelNivel = new System.Windows.Forms.Label();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 4;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanel1.Controls.Add(this.labelNume, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.label2, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelVenit, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.label4, 2, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelNivel, 3, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(42, 41);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(766, 183);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelNume
            // 
            this.labelNume.AutoSize = true;
            this.tableLayoutPanel1.SetColumnSpan(this.labelNume, 4);
            this.labelNume.Location = new System.Drawing.Point(6, 0);
            this.labelNume.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.labelNume.Name = "labelNume";
            this.labelNume.Size = new System.Drawing.Size(97, 41);
            this.labelNume.TabIndex = 0;
            this.labelNume.Text = "label1";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(178, 41);
            this.label2.TabIndex = 1;
            this.label2.Text = "Venit pe ora";
            // 
            // labelVenit
            // 
            this.labelVenit.AutoSize = true;
            this.labelVenit.Location = new System.Drawing.Point(194, 91);
            this.labelVenit.Name = "labelVenit";
            this.labelVenit.Size = new System.Drawing.Size(97, 41);
            this.labelVenit.TabIndex = 2;
            this.labelVenit.Text = "label3";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(385, 91);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(84, 41);
            this.label4.TabIndex = 3;
            this.label4.Text = "Nivel";
            // 
            // labelNivel
            // 
            this.labelNivel.AutoSize = true;
            this.labelNivel.Location = new System.Drawing.Point(576, 91);
            this.labelNivel.Name = "labelNivel";
            this.labelNivel.Size = new System.Drawing.Size(97, 41);
            this.labelNivel.TabIndex = 4;
            this.labelNivel.Text = "label5";
            // 
            // UserControlAngajat
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(17F, 41F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.Name = "UserControlAngajat";
            this.Padding = new System.Windows.Forms.Padding(42, 41, 42, 41);
            this.Size = new System.Drawing.Size(850, 265);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private TableLayoutPanel tableLayoutPanel1;
        private Label labelNume;
        private Label label2;
        private Label labelVenit;
        private Label label4;
        private Label labelNivel;
    }
}
