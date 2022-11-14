package com.example.nanny.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UserModel (
    @PrimaryKey()
    val user:String,
    @ColumnInfo(name="clave")
    val pass:String,
    @ColumnInfo(name="direccion")
    val address:String,
    @ColumnInfo(name = "nombre")
    val name:String,
    @ColumnInfo(name = "celular")
    val phone:String,
    @ColumnInfo(name = "rol")
    val rol:String


    )