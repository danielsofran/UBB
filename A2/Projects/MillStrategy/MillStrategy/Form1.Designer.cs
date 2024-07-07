namespace MillStrategy
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
            this.tablaControl1 = new MillStrategy.UI.Controls.TablaControl();
            this.SuspendLayout();
            // 
            // tablaControl1
            // 
            this.tablaControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tablaControl1.Location = new System.Drawing.Point(0, 0);
            this.tablaControl1.Name = "tablaControl1";
            this.tablaControl1.Size = new System.Drawing.Size(705, 699);
            this.tablaControl1.TabIndex = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(705, 699);
            this.Controls.Add(this.tablaControl1);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private UI.Controls.TablaControl tablaControl1;
    }
}

