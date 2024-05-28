package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.database.dao.RandomUserDao
import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.data.network.RandomUserService
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.TransformObject
import javax.inject.Inject

class RandomUserRepository @Inject constructor(
    private val api: RandomUserService,
    private val randomUserDao: RandomUserDao
){

    suspend fun getRandomUserFromApi(): RandomUser? {
        val response: RandomUserModel? = api.getRandomUser()
        return response?.let { TransformObject.fromModelToUser(it) }
    }

    /*
    suspend fun getRandomUserFromDatabase(): List<RandomUser> {

    }

     */

}