package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.util.Log
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    private var logTag = this::class.java.toString()
    private lateinit var randomUserUseCase: RandomUserUseCase

    fun initializeViewModel(){
        randomUserUseCase = RandomUserUseCase()
        callRandomUserUseCase()
    }


    override fun callRandomUserUseCase(){
        //Aquí llamamos a la recogida de datos
        randomUserUseCase.fetchRandomUserData()
    }

}