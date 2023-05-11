package com.example.appartementcomplex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("select * from user_table")
    fun getAll(): List<User>


    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user_table WHERE email= :email AND password= :password")
    suspend fun getUserByName(email: String, password: String): User?
}