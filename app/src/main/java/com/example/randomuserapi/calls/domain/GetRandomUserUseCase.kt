package com.example.randomuserapi.calls.domain

import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.domain.model.RandomUser
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(
    private val repository:RandomUserRepository
) {

    suspend operator fun invoke(): RandomUser? = repository.getRandomUserFromApi()

}