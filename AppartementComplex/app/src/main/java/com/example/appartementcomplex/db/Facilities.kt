package com.example.appartementcomplex.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "facilities")
data class Facilities(
    @PrimaryKey val id: Int,
    val facilitiesName: String,
    val bookingAmount: String,
    val urlImage: String
    )
