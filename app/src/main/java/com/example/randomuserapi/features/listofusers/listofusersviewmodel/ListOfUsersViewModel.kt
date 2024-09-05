package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapi.calls.data.RandomUserRepository
import com.example.randomuserapi.calls.domain.GetRandomUserUseCase
import com.example.randomuserapi.calls.domain.model.RandomUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfUsersViewModel @Inject constructor(
    private val randomUserUseCase: GetRandomUserUseCase,
    private val repository: RandomUserRepository
): ViewModel(), ListOfUsersViewModelInterface {

    private val logTag = this.javaClass.name

    private var userListPrepared: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var allUsersRecieved: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var isNetworkAvailable: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var listOfUsers: ArrayList<RandomUser> = ArrayList()

    override fun initializeViewModel(){
        this.userListPrepared.value = false
        this.allUsersRecieved.value = true
        this.isNetworkAvailable.value = true
    }

    override fun getRandomUserListFromApiCall(numberOfUsers: Int) {

        val fetchRandomUserData = Job()

        val errorHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
            println("Error ---> ${throwable.message}")
            isNetworkAvailable.postValue(false)
        }

        val scope = CoroutineScope(fetchRandomUserData + Dispatchers.Main)

        scope.launch(errorHandler){

            repository.clearDatabase()

            for(position in 0 until numberOfUsers) {

                val result = randomUserUseCase()

                addUserRecievedToUserList(result)

                if (position + 1 == numberOfUsers) {

                    userListPrepared.postValue(true)

                    observeIfAllUsersHasBeenRecieved()

                    Log.d(logTag, "Cantidad de Usuarios recibidos: ${listOfUsers.size}")
                }
            }
        }
    }

    override fun addUserRecievedToUserList(randomUser: RandomUser?){

        randomUser?.let { user ->
            listOfUsers.add(user)
        } ?: run {
            allUsersRecieved.value = false
        }
    }

    override fun getRandomUserListFromDatabase(){
        viewModelScope.launch {
            listOfUsers = repository.getRandomUserFromDatabase()
            userListPrepared.postValue(listOfUsers.isNotEmpty())

            Log.d(logTag, "Cantidad de Usuarios cargados de la base de datos: ${listOfUsers.size}")
        }
    }

    override fun getRandomUserList(): ArrayList<RandomUser> {
        return this.listOfUsers
    }

    override fun observeUserListPreparedValue(): MutableLiveData <Boolean> {
        return this.userListPrepared
    }

    override fun clearDatabase(){
        viewModelScope.launch { repository.clearDatabase() }
    }

    override fun observeIfAllUsersHasBeenRecieved(): MutableLiveData <Boolean> {
        return this.allUsersRecieved
    }

    override fun observeNetworkAvailability(): MutableLiveData <Boolean> {
        return this.isNetworkAvailable
    }

}