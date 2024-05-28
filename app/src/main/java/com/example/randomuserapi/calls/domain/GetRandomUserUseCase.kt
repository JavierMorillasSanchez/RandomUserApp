package com.example.randomuserapi.calls.domain

import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.TransformObject
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(
    private val repository:RandomUserRepository
) {

    suspend fun clearRandomUserListFromDatabase(){
        repository.clearDatabase()
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

    suspend fun getRandomUserFromDatabase(): List<RandomUser>{

        return  repository.getRandomUserFromDatabase()
    }

}