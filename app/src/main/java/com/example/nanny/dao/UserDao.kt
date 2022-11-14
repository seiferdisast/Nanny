package com.example.nanny.dao

import androidx.room.*
import com.example.nanny.model.UserModel

@Dao
interface UserDao {

 @Query("SELECT * FROM 'usuarios' where user =:user")
 fun consult(user:String):UserModel

 @Update
 fun update(user:UserModel)
 @Insert
 fun insert(user:UserModel)
 @Delete
 fun del(user:UserModel)

}