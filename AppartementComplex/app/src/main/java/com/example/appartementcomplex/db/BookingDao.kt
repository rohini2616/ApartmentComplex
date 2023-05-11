package com.example.appartementcomplex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertBooking(booking: Booking)

    @Query("SELECT * FROM booking")
    fun getAllBooking(): Flow<List<Booking>?>

    @Query("SELECT * FROM booking where name= :name AND status= :status AND startTime= :startTime AND endTime= :endTime")
    fun checkBookingStatus(
        name: String,
        status: Boolean,
        startTime: Long,
        endTime: Long
    ): Booking
}