package com.example.nanny.dao

import androidx.room.*
import com.example.nanny.model.UserModel

@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun consult():List<UserModel>

    @Update
    fun update(user:UserModel)
    @Insert
    fun insert(user: UserModel)
    @Delete
    fun delete(user: UserModel)
}