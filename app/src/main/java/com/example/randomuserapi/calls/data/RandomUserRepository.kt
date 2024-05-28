package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.data.network.RandomUserService
import javax.inject.Inject

class RandomUserRepository @Inject constructor(
    private val api: RandomUserService,
    private val randomUserProvider: RandomUserProvider
){

    suspend fun getRandomUser(): RandomUserModel? {
        val response = api.getRandomUser()
        randomUserProvider.randomUser.plus(response)
        return response
    }

}