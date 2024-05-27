package com.example.randomuserapi.calls.domain

import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.data.entities.RandomUserEntity

class GetRandomUserUseCase {

    private val repository = RandomUserRepository()

    suspend operator fun invoke(): RandomUserEntity? = repository.getRandomUser()

}