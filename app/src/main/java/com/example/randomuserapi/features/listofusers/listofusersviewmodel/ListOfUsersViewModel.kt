package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import android.widget.Toast
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random

class ListOfUsersViewModel: ListOfUsersViewModelInterface {

    lateinit var randomUserUseCase: RandomUserUseCase

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
    }

}