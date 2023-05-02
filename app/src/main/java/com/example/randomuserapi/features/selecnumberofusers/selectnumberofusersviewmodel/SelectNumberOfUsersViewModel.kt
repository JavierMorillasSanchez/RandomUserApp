package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.util.Log
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCaseRetrofit
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    var logTag = this::class.java.toString()
    var randomUserUseCaseRetrofit = RandomUserUseCaseRetrofit()

    override fun callRandomUserUseCaseRetrofit(){
        //This will make a call to API using Retrofit
        randomUserUseCaseRetrofit.fetchRandomUserDaraRetrofit()
    }


    override fun callRandomUserUseCase(){
        //This will make a call to API without using Retrofit
        RandomUserUseCase().fetchRandomUserData().start()
    }


}