package com.example.mobileandroid.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobileandroid.dto.TicketDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM tickets")
    fun getAll(): Flow<List<TicketDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ticket: TicketDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tickets: List<TicketDto>)

    @Update
    fun update(ticket: TicketDto): Int

    @Query("DELETE FROM tickets WHERE uuid = :uuid")
    fun deleteById(uuid: String): Int

    @Query("DELETE FROM tickets")
    fun deleteAll()
}