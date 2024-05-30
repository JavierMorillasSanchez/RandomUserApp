package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.randomuserapi.calls.domain.GetRandomUserUseCase
import com.example.randomuserapi.calls.domain.model.RandomUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach


@ExperimentalCoroutinesApi
class ListOfUsersViewModelTest{

    @RelaxedMockK
    private lateinit var randomUserUseCase: GetRandomUserUseCase

    private lateinit var viewModel: ListOfUsersViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = ListOfUsersViewModel(randomUserUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when the api call results on a null user, call notAllUsersRecieved method`() = runTest {

        val numberOfTimesApiCalled = 1
        val nullRandomUser: RandomUser? = null
        val allUsersRecievedValue = viewModel.checkIfAllUsersHasBeenRecieved().value

        //Given
        coEvery { randomUserUseCase.getRandomUserFromApi() } returns nullRandomUser

        //When
        viewModel.getRandomUserListFromApiCall(numberOfTimesApiCalled)

        //Then
        assert(allUsersRecievedValue == false)

    }

}