package com.example.randomuserapi.calls.di

import android.content.Context
import androidx.room.Room
import com.example.randomuserapi.calls.data.database.RandomUserDatabase
import com.example.randomuserapi.utils.RoomUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RandomUserDatabase::class.java,
        RoomUtils.RANDOM_USER_TABLE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesRandomUserDao(database: RandomUserDatabase) = database.getRandomUserDao()

}