namespace DevizeBiciclete
{
    partial class TestForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(TestForm));
            DevizeBiciclete.Domain.DevizData devizData1 = new DevizeBiciclete.Domain.DevizData();
            DevizeBiciclete.Domain.DevizData.BicicletaData bicicletaData1 = new DevizeBiciclete.Domain.DevizData.BicicletaData();
            DevizeBiciclete.Domain.DevizData.ClientData clientData1 = new DevizeBiciclete.Domain.DevizData.ClientData();
            DevizeBiciclete.Domain.DevizData.ConstatareData constatareData1 = new DevizeBiciclete.Domain.DevizData.ConstatareData();
            DevizeBiciclete.Repo.Repository repository1 = new DevizeBiciclete.Repo.Repository();
            this.button1 = new System.Windows.Forms.Button();
            this.piesaListControl1 = new DevizeBiciclete.UI.Controls.PiesaListControl();
            this.label1 = new System.Windows.Forms.Label();
            this.devizControl1 = new DevizeBiciclete.UI.DevizControl();
            this.manoperaListControl1 = new DevizeBiciclete.UI.Controls.ManoperaListControl();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(1158, 282);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(217, 66);
            this.button1.TabIndex = 0;
            this.button1.Text = "Generate PDF";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // piesaListControl1
            // 
            this.piesaListControl1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.piesaListControl1.Location = new System.Drawing.Point(14, 14);
            this.piesaListControl1.Margin = new System.Windows.Forms.Padding(5);
            this.piesaListControl1.Name = "piesaListControl1";
            this.piesaListControl1.Size = new System.Drawing.Size(447, 320);
            this.piesaListControl1.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(1192, 409);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(91, 38);
            this.label1.TabIndex = 3;
            this.label1.Text = "label1";
            // 
            // devizControl1
            // 
            bicicletaData1.Culoare = "";
            bicicletaData1.Marca = "";
            bicicletaData1.Model = "";
            bicicletaData1.Serie = "";
            devizData1.Bicicleta = bicicletaData1;
            clientData1.Adresa = "";
            clientData1.Denumire = "";
            clientData1.Nume = "";
            clientData1.PersoanaFizica = true;
            clientData1.PersoanaJuridica = false;
            clientData1.Registru = "";
            clientData1.RO = "";
            clientData1.Telefon = "";
            clientData1.TelefonFrima = "";
            devizData1.Client = clientData1;
            constatareData1.DataIn = new System.DateTime(((long)(0)));
            constatareData1.DataOut = new System.DateTime(((long)(0)));
            constatareData1.Motiv = "";
            devizData1.Constatare = constatareData1;
            devizData1.Enters = ((uint)(0u));
            devizData1.Numar = ((long)(0));
            devizData1.TVA = 0.19F;
            this.devizControl1.Deviz = devizData1;
            this.devizControl1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.devizControl1.Location = new System.Drawing.Point(899, 32);
            this.devizControl1.Margin = new System.Windows.Forms.Padding(5);
            this.devizControl1.Name = "devizControl1";
            repository1.Path = "";
            this.devizControl1.Repo = repository1;
            this.devizControl1.Size = new System.Drawing.Size(520, 211);
            this.devizControl1.TabIndex = 4;
            // 
            // manoperaListControl1
            // 
            this.manoperaListControl1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.manoperaListControl1.Location = new System.Drawing.Point(452, 14);
            this.manoperaListControl1.Margin = new System.Windows.Forms.Padding(5);
            this.manoperaListControl1.Name = "manoperaListControl1";
            this.manoperaListControl1.Size = new System.Drawing.Size(520, 334);
            this.manoperaListControl1.TabIndex = 5;
            // 
            // TestForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(15F, 37F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(1457, 592);
            this.Controls.Add(this.devizControl1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.piesaListControl1);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.manoperaListControl1);
            this.Font = new System.Drawing.Font("Segoe UI", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Name = "TestForm";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Button button1;
        private Label label1;
        public UI.DevizControl devizControl1;
        public UI.Controls.PiesaListControl piesaListControl1;
        private UI.Controls.ManoperaListControl manoperaListControl1;
    }
}