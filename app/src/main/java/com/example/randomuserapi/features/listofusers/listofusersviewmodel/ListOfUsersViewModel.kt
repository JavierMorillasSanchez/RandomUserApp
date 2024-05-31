package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapi.calls.domain.GetRandomUserUseCase
import com.example.randomuserapi.calls.domain.model.RandomUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfUsersViewModel @Inject constructor(
    private val randomUserUseCase: GetRandomUserUseCase
): ViewModel(), ListOfUsersViewModelInterface {

    private val logTag = this.javaClass.name

    private var userListPrepared: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var allUsersRecieved: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var listOfUsers: ArrayList<RandomUser> = ArrayList()

    override fun initializeViewModel(){
        this.userListPrepared.value = false
        this.allUsersRecieved.value = true
    }

    override fun getRandomUserListFromApiCall(numberOfUsers: Int) {

        viewModelScope.launch {

            clearDatabase()

            for(position in 0 until numberOfUsers) {

                var result = randomUserUseCase.getRandomUserFromApi()

                addUserRecievedToUserList(result)

                    if (position + 1 == numberOfUsers) {

                        userListPrepared.postValue(true)

                        checkIfAllUsersHasBeenRecieved()

                        //Log.d(logTag, "Cantidad de Usuarios recibidos: ${listOfUsers.size}")
                    }

                }
            }
    }

    override fun addUserRecievedToUserList(randomUser: RandomUser?){

        if (randomUser != null) {

            listOfUsers.add(randomUser)

        } else {
            allUsersRecieved.value = false
        }
    }

    override fun getRandomUserListFromDatabase(){
        viewModelScope.launch {
            listOfUsers = randomUserUseCase.getRandomUserFromDatabase()
            userListPrepared.postValue(listOfUsers.isNotEmpty())

            Log.d(logTag, "Cantidad de Usuarios cargados de la base de datos: ${listOfUsers.size}")
        }
    }

    override fun getRandomUserList(): ArrayList<RandomUser> {
        return this.listOfUsers
    }

    override fun getUserListPreparedValue(): MutableLiveData <Boolean> {
        return this.userListPrepared
    }

    override fun clearDatabase(){
        viewModelScope.launch { randomUserUseCase.clearRandomUserListFromDatabase() }
    }

    override fun checkIfAllUsersHasBeenRecieved(): MutableLiveData <Boolean> {
        return this.allUsersRecieved
    }

}