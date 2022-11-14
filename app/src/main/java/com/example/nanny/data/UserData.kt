package com.example.nanny.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nanny.dao.UserDao
import com.example.nanny.model.UserModel

@Database(
    entities=[UserModel::class],
    version=1, exportSchema = false
    )
abstract class UserData:RoomDatabase() {
        abstract val userDao:UserDao
        companion object{
            const val DATABASE_NAME="bdnannys"
        }
}



