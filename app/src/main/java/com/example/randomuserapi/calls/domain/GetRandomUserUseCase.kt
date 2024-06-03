package com.example.randomuserapi.calls.domain

import android.util.Log
import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.TransformObject
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(
    private val repository:RandomUserRepository
) {

    private val logTag = this.javaClass.name

    suspend operator fun invoke(): RandomUser?{

        val randomUser = repository.getRandomUserFromApi()

        if(randomUser != null){
            repository.insertRandomUserOnDatabase(
                TransformObject.fromUserToDatabaseEntity(randomUser)
            )
        }

        randomUser?.let { Log.d(logTag, "Usuario recibido de la llamada: ${randomUser.getRandomUserFullName()}") }

        return randomUser
    }

}