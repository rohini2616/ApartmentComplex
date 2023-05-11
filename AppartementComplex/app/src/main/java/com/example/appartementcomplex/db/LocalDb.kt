package com.example.appartementcomplex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Facilities::class, Booking::class], version = 2)
abstract class LocalDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun facilityDao(): FacilitiesDao
    abstract fun bookingDao(): BookingDao

    companion object {
        private var localDb: LocalDb? = null

        @Synchronized
        fun getInstance(context: Context): LocalDb {
            if (localDb == null)
                localDb = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDb::class.java,
                    "apartment_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            return localDb!!

        }

    }


}