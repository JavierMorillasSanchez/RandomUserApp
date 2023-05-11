package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListOfUsersViewModel: ListOfUsersViewModelInterface {

    var logTag = this::class.java.toString()

    private lateinit var randomUserUseCase: RandomUserUseCase

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
    }

    override suspend fun populateRandomUserArray(numberOfUsers: Int){
        /*
        for(call in 1..numberOfUsers){
            this.randomUserUseCase.fetchRandomUserDataFromApi()
        }

         */
    }

}