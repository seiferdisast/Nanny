package com.example.nanny.model

import android.location.Address
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users")
class UserModel(
    @ColumnInfo(name = "names")
    val names: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "roll")
    val roll: String
)