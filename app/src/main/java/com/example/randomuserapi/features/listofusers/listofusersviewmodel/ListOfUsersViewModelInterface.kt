package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import androidx.lifecycle.MutableLiveData
import com.example.randomuserapi.calls.domain.model.RandomUser

interface ListOfUsersViewModelInterface {
    fun initializeViewModel()
    fun randomUserApiCall(numberOfUsers: Int)
    fun getUserListPreparedValue(): MutableLiveData<Boolean>
    fun getRandomUserList(): ArrayList<RandomUser>
    fun clearDatabase()
}