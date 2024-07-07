package com.example.mobileandroid.dto

import java.io.Serializable

data class TicketDto(
    val uuid: String = "",
    val name: String = "",
    val description: String = "",
    val complexity: Int = 0,
    val isDone: Boolean = false,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val imagesUrls: List<String> = listOf(),
    val created: String = "",
    val updated: String = "") : Serializable