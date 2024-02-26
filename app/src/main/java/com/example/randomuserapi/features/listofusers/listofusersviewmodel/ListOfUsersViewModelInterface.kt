package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import androidx.lifecycle.MutableLiveData
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser

interface ListOfUsersViewModelInterface {
    fun initializeViewModel()
    fun randomUserCall(numberOfUsers: Int)
    fun checkIfUserListPrepared(): MutableLiveData<Boolean>
    fun getRandomUserList(): ArrayList<RandomUser>
}