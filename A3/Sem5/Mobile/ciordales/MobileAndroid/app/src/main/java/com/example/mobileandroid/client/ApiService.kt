package com.example.mobileandroid.client;

import com.example.mobileandroid.dto.LoginRequest
import com.example.mobileandroid.dto.LoginResponse
import com.example.mobileandroid.dto.TicketDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun login(@Body username: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("tickets/all")
    fun getTickets(@Header("Authorization") authorization: String): Call<List<TicketDto>>

    @Headers("Content-Type: application/json")
    @PATCH("tickets")
    fun updateTicket(@Header("Authorization") authorization: String, @Body ticket: TicketDto): Call<TicketDto>
}
