package com.example.mobileandroid.dto

import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class TicketDto(
    @PrimaryKey
    val uuid: String = "",
    val name: String = "",
    val description: String = "",
    val complexity: Int = 0,
    val isDone: Boolean = false,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val created: String = "",
    val updated: String = "") : Serializable