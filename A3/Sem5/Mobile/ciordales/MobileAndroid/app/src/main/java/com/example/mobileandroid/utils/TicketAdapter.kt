package com.example.mobileandroid.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileandroid.R
import com.example.mobileandroid.dto.TicketDto

class TicketAdapter(private val mList: MutableList<TicketDto>, private val onItemClick: (TicketDto) -> Unit) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ticket = mList[position]

        if (ticket.isDone) {
            holder.mainLayout.setBackgroundColor(Color.GREEN)
        } else {
            holder.mainLayout.setBackgroundColor(Color.RED)
        }
        holder.nameView.text = ticket.name
        holder.descriptionView.text = ticket.description
        holder.complexityView.text = ticket.complexity.toString()
        holder.itemView.setOnClickListener {
            onItemClick.invoke(ticket)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateTicket(updatedTicket: TicketDto) {
        val position = mList.indexOfFirst { it.uuid == updatedTicket.uuid }
        if (position != -1) {
            mList[position] = updatedTicket
            notifyItemChanged(position)
        }
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mainLayout: LinearLayout = itemView.findViewById(R.id.ll_main_layout)
        val nameView: TextView = itemView.findViewById(R.id.text_name)
        val descriptionView: TextView = itemView.findViewById(R.id.text_description)
        val complexityView: TextView = itemView.findViewById(R.id.text_complexity)
    }
}