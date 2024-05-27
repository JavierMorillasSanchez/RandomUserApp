package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.entities.RandomUserEntity
import com.example.randomuserapi.calls.data.network.RandomUserService
import javax.inject.Inject

class RandomUserRepository @Inject constructor(
    private val api: RandomUserService,
    private val randomUserProvider: RandomUserProvider
){

    suspend fun getRandomUser(): RandomUserEntity? {
        val response = api.getRandomUser()
        randomUserProvider.randomUser.plus(response)
        return response
    }

}