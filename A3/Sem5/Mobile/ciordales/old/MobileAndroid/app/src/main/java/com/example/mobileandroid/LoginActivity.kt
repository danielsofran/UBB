package com.example.mobileandroid

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileandroid.client.ApiClient
import com.example.mobileandroid.client.Api
import com.example.mobileandroid.dto.LoginRequest
import com.example.mobileandroid.dto.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        Api.token = sharedPreferences.getString("token", null)
        if (Api.token != null) {
            val intent = Intent(this@LoginActivity, TicketsActivity::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_login)

        val textUsername = findViewById<EditText>(R.id.et_user_name)
        val textPassword = findViewById<EditText>(R.id.et_password)
        val buttonReset = findViewById<Button>(R.id.btn_reset)
        val buttonSubmit = findViewById<Button>(R.id.btn_submit)
        val textError = findViewById<TextView>(R.id.text_error)

        buttonReset.setOnClickListener {
            textUsername.setText("")
            textPassword.setText("")
        }

        buttonSubmit.setOnClickListener {
            val username = textUsername.text.toString()
            val password = textPassword.text.toString()

            val call = ApiClient.apiService.login(LoginRequest(username, password))
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        Toast.makeText(this@LoginActivity, "Token: $token", Toast.LENGTH_LONG).show()
                        Api.token = token
                        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("key_int", token)

                        val intent = Intent(this@LoginActivity, TicketsActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                        textError.text = "login failed"
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Network error", Toast.LENGTH_LONG).show()
                    textError.text = "network error"
                }
            })
        }
    }
}