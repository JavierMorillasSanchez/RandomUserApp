package com.example.randomuserapi.calls.data

import android.util.Log
import com.example.randomuserapi.calls.data.database.dao.RandomUserDao
import com.example.randomuserapi.calls.data.database.entities.RandomUserEntity
import com.example.randomuserapi.calls.data.network.RandomUserService
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.TransformObject
import javax.inject.Inject

class RandomUserRepository @Inject constructor(
    private val api: RandomUserService,
    private val randomUserDao: RandomUserDao
){

    private val logTag = this.javaClass.name

    suspend fun getRandomUserFromApi(): RandomUser? {
        val response = api.getRandomUser()
        return response?.let { TransformObject.fromModelToUser(it) }
    }


    suspend fun getRandomUserFromDatabase(): ArrayList<RandomUser> {

        val response = randomUserDao.getAllUsers()

        val randomUserList = ArrayList<RandomUser>()

        for(randomUser in response) {
            randomUserList.add(TransformObject.fromEntityToUser(randomUser))
        }

        Log.d(logTag, "Tamaño Lista de Usuarios: ${randomUserList.size}")

        return randomUserList
    }

    suspend fun insertRandomUserOnDatabase(randomUserEntity: RandomUserEntity) {
        randomUserDao.insertSingleRandomUser(randomUserEntity)
    }

    suspend fun clearDatabase(){
        randomUserDao.clearDatabase()
    }


}