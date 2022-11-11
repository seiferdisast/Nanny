package com.example.nanny.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import com.example.nanny.dao.UserDAO
import com.example.nanny.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract val userDAO: UserDAO

    companion object {
        const val DATABASE_NAME = "DbUsersNanny"
    }
}