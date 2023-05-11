package com.example.appartementcomplex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FacilitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<Facilities>)

    @Query("SELECT * FROM facilities")
    fun getAllFacilities(): List<Facilities>?
}