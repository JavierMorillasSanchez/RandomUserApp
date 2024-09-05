package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.randomuserapi.calls.domain.model.RandomUser

interface ListOfUsersViewModelInterface {
    fun initializeViewModel()
    fun getRandomUserListFromApiCall(numberOfUsers: Int)
    fun getRandomUserListFromDatabase()
    fun addUserRecievedToUserList(randomUser: RandomUser?)
    fun getRandomUserList(): ArrayList<RandomUser>
    fun clearDatabase()
    fun observeIfAllUsersHasBeenRecieved(): MutableLiveData <Boolean>
    fun observeNetworkAvailability(): MutableLiveData <Boolean>
    fun observeUserListPreparedValue(): MutableLiveData<Boolean>
}