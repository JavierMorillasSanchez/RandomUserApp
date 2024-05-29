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

    suspend fun clearRandomUserListFromDatabase(){
        repository.clearDatabase()
        Log.d(logTag, "Base de datos limpiada")
    }

    suspend fun getRandomUserFromApi(): RandomUser?{

        val randomUser = repository.getRandomUserFromApi()

        if(randomUser != null){
            repository.insertRandomUserOnDatabase(
                TransformObject.fromUserToDatabaseEntity(randomUser)
            )
        }

        return randomUser
    }

    suspend fun getRandomUserFromDatabase(): ArrayList<RandomUser>{
        Log.d(logTag, "Cantidad de Usuarios guardado: ${repository.getRandomUserFromDatabase().size}")
        return  repository.getRandomUserFromDatabase()
    }

}