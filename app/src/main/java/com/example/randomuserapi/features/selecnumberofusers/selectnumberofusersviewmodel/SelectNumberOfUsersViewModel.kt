package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCaseRetrofit

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    var logTag = this::class.java.toString()
    var randomUserUseCaseRetrofit = RandomUserUseCaseRetrofit()

    override fun callRandomUserUseCaseRetrofit(){
        //This will make a call to API using Retrofit
        randomUserUseCaseRetrofit.fetchRandomUserDataRetrofit()
    }


    override fun callRandomUserUseCase(){
        //This will make a call to API without using Retrofit
        RandomUserUseCase().fetchRandomUserData().start()
    }


}