package com.example.mobileandroid

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mobileandroid.client.Api
import com.example.mobileandroid.client.ApiClient
import com.example.mobileandroid.dto.TicketDto
import com.example.mobileandroid.utils.MyAppDatabase
import com.example.mobileandroid.utils.MyWorker
import com.example.mobileandroid.utils.TicketAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

class TicketsActivity : AppCompatActivity() {

    val log: Logger = Logger.getLogger(TicketsActivity::class.java.name)
    val recyclerview: RecyclerView by lazy { findViewById(R.id.recyclerview) }

    private val channelId = "TICKETS_CHANNEL"

    private val REQUEST_EDIT_TICKET = 1
    private val REQUEST_CAMERA = 2
    private var imageFilePath: String? = null

    private val database: MyAppDatabase by lazy { MyAppDatabase.getDatabase(this) }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Your Channel Name" // Replace with your channel name
            val descriptionText = "Your Channel Description" // Replace with your channel description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        // Create the notification channel
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My notification")
            .setContentText("Application started")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 777
        notificationManager.notify(notificationId, builder.build())

        recyclerview.layoutManager = LinearLayoutManager(this)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = isOnline(this)


        val btnCamera = findViewById<Button>(R.id.btn_photo)
        btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(0, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        val token = Api.token

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            logout()
        }

        val call = ApiClient.apiService.getTickets("Bearer $token")

        if ("TRANSPORT_WIFI".equals(isOnline(this))) {
            call.enqueue(object : Callback<List<TicketDto>> {
                override fun onResponse(
                    call: Call<List<TicketDto>>,
                    response: Response<List<TicketDto>>
                ) {
                    if (response.isSuccessful) {
                        val tickets = response.body()
                        val ticketsArray = Array<TicketDto>(tickets!!.size) { tickets[it] }
                        log.warning(tickets.toString())
                        database.ticketDao().insert(ticketsArray.toList())
                        val adapter = TicketAdapter(ticketsArray.toMutableList()) { ticket ->
                            val intent =
                                Intent(this@TicketsActivity, EditTicketActivity::class.java)
                            intent.putExtra("ticket", ticket)
                            startActivityForResult(intent, 1)
                        }
                        recyclerview.adapter = adapter
                    } else {
                        Toast.makeText(
                            this@TicketsActivity,
                            "Get tickets failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<TicketDto>>, t: Throwable) {
                    Toast.makeText(this@TicketsActivity, "Network error", Toast.LENGTH_LONG).show()
                    log.warning(t.toString())
                }
            })
        } else {

            lifecycleScope.launch {
                database.ticketDao().getAll().collect { newTickets ->

                    val adapter = TicketAdapter(newTickets.toMutableList()) { ticket ->
                        val intent = Intent(this@TicketsActivity, EditTicketActivity::class.java)
                        intent.putExtra("ticket", ticket)
                        startActivityForResult(intent, 1)
                    }

                    recyclerview.adapter = adapter
                }
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            val photoFile = createImageFile()
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.mobileandroid.fileprovider",
                photoFile
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePictureIntent, REQUEST_CAMERA)
        } catch (ex: Exception) {
            // Error occurred while creating the File
            ex.printStackTrace()
        }
    }

    @Throws(Exception::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file path for use with ACTION_VIEW intents
            imageFilePath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_EDIT_TICKET && resultCode == Activity.RESULT_OK) {
            log.warning("Edit Activity finished")
            val editedTicket = data?.getSerializableExtra("editedTicket") as TicketDto
            Toast.makeText(this, "Ticket ${editedTicket.name} updated", Toast.LENGTH_SHORT).show()

            (recyclerview.adapter as? TicketAdapter)?.updateTicket(editedTicket)
        }
        if(requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                log.warning("Camera Activity finished")
                Toast.makeText(this, "Photo saved at $imageFilePath", Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, "Camera intent returned $resultCode", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isOnline(context: Context): String {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return "TRANSPORT_CELLULAR"
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return "TRANSPORT_WIFI"
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return "TRANSPORT_ETHERNET"
                }
            }
        }
        return "NO_CONNECTION"
    }

    private fun logout() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}