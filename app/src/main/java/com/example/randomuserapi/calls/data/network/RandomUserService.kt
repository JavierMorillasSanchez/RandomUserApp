package com.example.randomuserapi.calls.data.network

import com.example.randomuserapi.calls.data.model.RandomUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomUserService @Inject constructor(
    private val apiClient: RandomUserApiClient
) {

    suspend fun getRandomUser(): RandomUserModel? {

        return withContext(Dispatchers.IO){
            val response = apiClient.getRandomUserDataCall()
            response.body()
        }

    }

}