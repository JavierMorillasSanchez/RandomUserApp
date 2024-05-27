package com.example.randomuserapi.calls.data.network

import com.example.randomuserapi.calls.RetrofitHelper
import com.example.randomuserapi.calls.data.entities.RandomUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomUserService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getRandomUser(): RandomUserEntity? {

        return withContext(Dispatchers.IO){
            val response = retrofit.create(RandomUserApiClient::class.java).getRandomUserDataCall()
            response.body()
        }

    }

}