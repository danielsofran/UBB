package com.example.mobileandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mobileandroid.client.Api
import com.example.mobileandroid.client.ApiClient
import com.example.mobileandroid.dto.TicketDto
import com.example.mobileandroid.utils.MyAppDatabase
import java.util.logging.Logger

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditTicketActivity : AppCompatActivity() {

    private val log: Logger = Logger.getLogger(EditTicketActivity::class.java.name)

    private val database: MyAppDatabase by lazy { MyAppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ticket)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val nameField = findViewById<EditText>(R.id.et_name)
        val descriptionField = findViewById<EditText>(R.id.et_description)
        val complexityField = findViewById<EditText>(R.id.et_complexity)
        val isDoneField = findViewById<CheckBox>(R.id.cb_done)
        val buttonSubmit = findViewById<Button>(R.id.btn_save)

        val token = Api.token

        val ticket = intent.getSerializableExtra("ticket", TicketDto::class.java)

        buttonSubmit.setOnClickListener {
            val call = ApiClient.apiService.updateTicket("Bearer $token", TicketDto(
                uuid = ticket?.uuid ?: "",
                name = nameField.text.toString(),
                description = descriptionField.text.toString(),
                complexity = complexityField.text.toString().toInt(),
                isDone = isDoneField.isChecked
            ))
            call.enqueue(object : Callback<TicketDto> {
                override fun onResponse(call: Call<TicketDto>, response: Response<TicketDto>) {
                    if (response.isSuccessful) {
                        val editedTicketData = response.body()
                        log.warning(ticket.toString())
                        val returnIntent = Intent()
                        returnIntent.putExtra("editedTicket", editedTicketData)
                        if (ticket != null) {
                            database.ticketDao().update(editedTicketData!!)
                        }
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    } else {
                        log.warning("Update ticket failed: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<TicketDto>, t: Throwable) {
                    log.warning(t.toString())
                }
            })
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (ticket != null) {
            nameField.setText(ticket.name)
            descriptionField.setText(ticket.description)
            complexityField.setText(ticket.complexity.toString())
            isDoneField.isChecked = ticket.isDone
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}