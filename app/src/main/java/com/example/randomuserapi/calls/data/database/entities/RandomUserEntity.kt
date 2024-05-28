package com.example.randomuserapi.calls.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomuserapi.utils.RoomUtils

@Entity(tableName = RoomUtils.RANDOM_USER_TABLE_NAME)
data class RandomUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "pictureLarge") val pictureLarge: String?,
    @ColumnInfo(name = "pictureMedium") val pictureMedium: String?,
    @ColumnInfo(name = "pictureThumbnail") val pictureThumbnail: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "nationality") val nationality: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "addressNumber") val addressNumber: String?,
    @ColumnInfo(name = "birthday") val birthday: String?,
    @ColumnInfo(name = "age") val age: String?,
)