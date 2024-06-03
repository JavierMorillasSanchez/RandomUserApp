package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.domain.GetRandomUserUseCase
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.utils.MockedUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext


@ExperimentalCoroutinesApi
@ExtendWith(ListOfUsersViewModelTest.InstantTaskExecutorRuleForJUnit5::class)
class ListOfUsersViewModelTest{

    @RelaxedMockK
    private lateinit var randomUserUseCase: GetRandomUserUseCase

    @RelaxedMockK
    private lateinit var repository: RandomUserRepository

    private var viewModelMock = mockk<ListOfUsersViewModel>()

    class InstantTaskExecutorRuleForJUnit5 : AfterEachCallback, BeforeEachCallback {
        override fun beforeEach(context: ExtensionContext?) {
            ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean {
                    return true
                }
            })
        }

        override fun afterEach(context: ExtensionContext?) {
            ArchTaskExecutor.getInstance().setDelegate(null)
        }
    }

    @BeforeEach
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModelMock = ListOfUsersViewModel(randomUserUseCase, repository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when the api call results on a null user, the user is not stored in randomUserList`() = runTest {

        val nullRandomUserRecieved = null

        //Given
        coEvery {
            randomUserUseCase()
            viewModelMock.addUserRecievedToUserList(nullRandomUserRecieved)
        }

        //Then
        assertTrue(viewModelMock.getRandomUserList().isEmpty())
    }

    @Test
    fun `when the api call results on a non null user, the user is stored in randomUserList`() = runTest {

        val randomUserRecieved = MockedUser.mockedUser

        //Given
        coEvery {
            randomUserUseCase()
            viewModelMock.addUserRecievedToUserList(randomUserRecieved)
        }

        //Then
        assertTrue(viewModelMock.getRandomUserList().isNotEmpty())
    }

    @Test
    fun `when the user list comes from Database, if there is no users, the variable userListPrepared is false`() = runTest {

        val userListNotPrepared = false
        val emptyUserListFromDatabase: ArrayList<RandomUser> = ArrayList()

        //Given
        coEvery {
            repository.getRandomUserFromDatabase()
        } returns emptyUserListFromDatabase

        //When
        viewModelMock.getRandomUserListFromDatabase()

        //Then
        assert(userListNotPrepared == viewModelMock.getUserListPreparedValue().value)
    }

    @Test
    fun `when the user list comes from Database, if there is at least one user, the variable userListPrepared is true`() = runTest {

        val userListPrepared = true
        val userListFromDatabase: ArrayList<RandomUser> = ArrayList()
        MockedUser.mockedUser?.let { userListFromDatabase.add(it) }

        //Given
        coEvery {
            repository.getRandomUserFromDatabase()
        } returns userListFromDatabase

        //When
        viewModelMock.getRandomUserListFromDatabase()

        //Then
        assert(userListPrepared == viewModelMock.getUserListPreparedValue().value)
    }


}