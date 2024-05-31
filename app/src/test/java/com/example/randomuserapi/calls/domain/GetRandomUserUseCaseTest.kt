package com.example.randomuserapi.calls.domain

import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.domain.model.RandomUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomUserUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: RandomUserRepository

    private lateinit var getRandomUserUseCase: GetRandomUserUseCase

    @BeforeEach
    fun onBefore(){
        MockKAnnotations.init(this)
        this.getRandomUserUseCase = GetRandomUserUseCase(repository)
    }

    @Test
    fun `when making a single call, the api returns null user`() = runBlocking{

        val userRecieved: RandomUser? = null
        //Given
        coEvery { repository.getRandomUserFromApi() } returns userRecieved

        //Then
        val response = getRandomUserUseCase.getRandomUserFromApi()

        //When
        assert(userRecieved == response )

    }

}