using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TemaCS.domain;
using TemaCS.service;

namespace TemaCS.controller
{
    public partial class LoginForm : Form
    {
        internal Service Service { private get; set; }

        public LoginForm()
        {
            InitializeComponent();
        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            Utils.Execute(delegate
            {
                User user = Service.Login(textBoxNume.Text, textBoxPassword.Text);
                MainForm mainForm = new MainForm()
                {
                    User= user,
                    Service= Service
                };
                mainForm.Show();
                this.Hide();
            });
        }
    }
}
