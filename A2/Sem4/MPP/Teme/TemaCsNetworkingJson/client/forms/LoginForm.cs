using client.controller;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace bilete.client
{
    public partial class LoginForm : Form
    {
        private BileteMainController controller;

        public LoginForm(BileteMainController controller)
        {
            InitializeComponent();
            this.controller = controller;
        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            string username = textBoxNume.Text;
            string password = textBoxPassword.Text;
            try
            {
                controller.login(username, password);
                
                MainForm mainForm = new MainForm(controller);
                mainForm.Text = username;
                mainForm.Show();
                this.Hide();
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, "Login error " + ex.Message + " " + ex.StackTrace, "Error");
            }
        }
    }
}
