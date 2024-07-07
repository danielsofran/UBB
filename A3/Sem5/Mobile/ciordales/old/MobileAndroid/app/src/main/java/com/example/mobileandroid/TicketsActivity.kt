package com.example.mobileandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileandroid.client.Api
import com.example.mobileandroid.client.ApiClient
import com.example.mobileandroid.dto.TicketDto
import com.example.mobileandroid.utils.TicketAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class TicketsActivity : AppCompatActivity() {

    val log: Logger = Logger.getLogger(TicketsActivity::class.java.name)
    val recyclerview: RecyclerView by lazy { findViewById(R.id.recyclerview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val token = Api.token

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            logout()
        }

        val call = ApiClient.apiService.getTickets("Bearer $token")

        call.enqueue(object : Callback<List<TicketDto>> {
            override fun onResponse(call: Call<List<TicketDto>>, response: Response<List<TicketDto>>) {
                if (response.isSuccessful) {
                    val tickets = response.body()
                    val ticketsArray = Array<TicketDto>(tickets!!.size) { tickets[it] }
                    log.warning(tickets.toString())
                    val adapter = TicketAdapter(ticketsArray.toMutableList()) { ticket ->
                        val intent = Intent(this@TicketsActivity, EditTicketActivity::class.java)
                        intent.putExtra("ticket", ticket)
                        startActivityForResult(intent, 1)
                    }
                    recyclerview.adapter = adapter
                } else {
                    Toast.makeText(this@TicketsActivity, "Get tickets failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TicketDto>>, t: Throwable) {
                Toast.makeText(this@TicketsActivity, "Network error", Toast.LENGTH_LONG).show()
                log.warning(t.toString())
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val editedTicket = data?.getSerializableExtra("editedTicket") as TicketDto
            Toast.makeText(this, "Ticket ${editedTicket.name} updated", Toast.LENGTH_SHORT).show()

            (recyclerview.adapter as? TicketAdapter)?.updateTicket(editedTicket)
        }
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