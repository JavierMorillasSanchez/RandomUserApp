package com.example.randomuserapi.calls.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuserapi.calls.data.database.entities.RandomUserEntity
import com.example.randomuserapi.utils.RoomUtils

@Dao
interface RandomUserDao {
    @Query("SELECT * FROM ${RoomUtils.RANDOM_USER_TABLE_NAME} ORDER BY id ASC")
    suspend fun getAllUsers(): List<RandomUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRandomUsers(randomUserList: List<RandomUserEntity>)
}