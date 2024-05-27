package com.example.randomuserapi.calls.data.network

import com.example.randomuserapi.calls.RetrofitHelper
import com.example.randomuserapi.calls.data.entities.RandomUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomUserService @Inject constructor(
    private val apiClient: RandomUserApiClient
) {

    suspend fun getRandomUser(): RandomUserEntity? {

        return withContext(Dispatchers.IO){
            val response = apiClient.getRandomUserDataCall()
            response.body()
        }

    }

}