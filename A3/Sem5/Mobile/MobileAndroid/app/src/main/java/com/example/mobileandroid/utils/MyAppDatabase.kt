package com.example.mobileandroid.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobileandroid.dto.TicketDto
import com.example.mobileandroid.repository.TicketDao

@Database(entities = arrayOf(TicketDto::class), version = 1)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketDao

    companion object {
        @Volatile
        private var INSTANCE: MyAppDatabase? = null

        fun getDatabase(context: Context): MyAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MyAppDatabase::class.java,
                    "app_database"
                )
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
