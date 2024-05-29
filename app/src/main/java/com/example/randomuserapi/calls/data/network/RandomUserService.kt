package com.example.randomuserapi.calls.data.network

import android.util.Log
import com.example.randomuserapi.calls.data.model.RandomUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomUserService @Inject constructor(
    private val apiClient: RandomUserApiClient
) {
    private val logTag = this.javaClass.name
    suspend fun getRandomUser(): RandomUserModel? {

        return withContext(Dispatchers.IO){
            val response = apiClient.getRandomUserDataCall()
            Log.d(logTag, "Respuesta Servicio Codigo -> ${response.code()}")
            response.body()
        }

    }

}