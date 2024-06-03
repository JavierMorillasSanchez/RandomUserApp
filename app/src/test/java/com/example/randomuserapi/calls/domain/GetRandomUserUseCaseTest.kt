package com.example.randomuserapi.calls.domain

import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.MockedUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Random

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
        val response = getRandomUserUseCase()

        //When
        assert(userRecieved == response )

    }

    @Test
    fun `when making a sigle call, the user recieved is stored on a ArrayList`() = runBlocking{

        val listOfUsers: ArrayList<RandomUser> = ArrayList()
        val mockedUser: RandomUser? = MockedUser.mockedUser

        //Given
        coEvery {
            repository.getRandomUserFromApi()
        } returns mockedUser

        //Then
        coEvery {
            getRandomUserUseCase()
            mockedUser?.let { listOfUsers.add(mockedUser) }
        }

        //When
        assert( listOfUsers[0] == mockedUser)

    }

    @Test
    fun `when making a single call and the api returns null user, the null user is not stored`() = runBlocking{

        val listOfUsers: ArrayList<RandomUser> = ArrayList()
        val mockedUser: RandomUser? = null

        //Given
        coEvery {
            repository.getRandomUserFromApi()
        } returns mockedUser

        //Then
        coEvery {
            getRandomUserUseCase()
            mockedUser?.let { listOfUsers.add(mockedUser) }
        }

        //When
        assert( listOfUsers.isEmpty())

    }

}