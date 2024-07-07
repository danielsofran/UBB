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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.mobileandroid.client.ApiClient
import com.example.mobileandroid.client.Api
import com.example.mobileandroid.dto.LoginRequest
import com.example.mobileandroid.dto.LoginResponse
import com.example.mobileandroid.utils.MyWorker
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

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

        // worker
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(3, TimeUnit.SECONDS)
            .setInitialDelay(0, TimeUnit.SECONDS)
            .build()
//        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
//            .setInitialDelay(0, TimeUnit.SECONDS)
//            .build()

        // Enqueue the WorkRequest
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        // Get the LiveData for observing the worker's output
        val workManager = WorkManager.getInstance(applicationContext)
        val workerLiveData = workManager.getWorkInfoByIdLiveData(workRequest.id)

        // Observe changes in the worker's output
        workerLiveData.observe(this) { workInfo ->
            if (workInfo == null) {
                return@observe
            }
            if (workInfo.state.isFinished) {
                val output = workInfo.outputData
                val networkStatus = output.getBoolean("networkStatus", false)
                Toast.makeText(this, "Network status: $networkStatus", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Work in progress ${workInfo.state}", Toast.LENGTH_SHORT).show()
            }
            // enqueue work request
            WorkManager.getInstance(applicationContext).enqueue(workRequest)
        }

    }
}