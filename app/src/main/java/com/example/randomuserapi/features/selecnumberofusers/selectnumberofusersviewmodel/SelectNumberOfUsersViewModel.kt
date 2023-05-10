package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.content.Context
import android.widget.Toast
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    private var logTag = this::class.java.toString()
    private lateinit var randomUserUseCase: RandomUserUseCase

    fun initializeViewModel(){
        randomUserUseCase = RandomUserUseCase()
    }

    override fun navigateToListOfUsers(context: Context, numberOfUsers: Int) {
        Toast.makeText(context, "Usuarios a mostrar: $numberOfUsers", Toast.LENGTH_SHORT).show()
    }

}