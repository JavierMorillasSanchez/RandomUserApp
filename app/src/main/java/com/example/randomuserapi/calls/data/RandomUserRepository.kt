package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.entities.RandomUserEntity
import com.example.randomuserapi.calls.data.network.RandomUserService

class RandomUserRepository {

    private var api = RandomUserService()

    suspend fun getRandomUser(): RandomUserEntity? {
        val response = api.getRandomUser()
        RandomUserProvider.randomUser.plus(response)
        return response
    }

}