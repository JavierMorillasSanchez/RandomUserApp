package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.util.Log
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase

class ListOfUsersViewModel: ListOfUsersViewModelInterface {

    var logTag = this::class.java.toString()

    private lateinit var randomUserUseCase: RandomUserUseCase

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
    }

    override fun populateRandomUserArray(numberOfUsers: Int){
        for (randomUser in 1..numberOfUsers){
            this.randomUserUseCase.fetchRandomUserData()
        }
    }

    










}