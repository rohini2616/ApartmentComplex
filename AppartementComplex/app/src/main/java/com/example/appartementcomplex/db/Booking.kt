package com.example.appartementcomplex.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking")
data class Booking(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val startTime: Long,
    val endTime: Long,
    val dateTimeString: String,
    val amount: String,
    val status: Boolean = false,
)
