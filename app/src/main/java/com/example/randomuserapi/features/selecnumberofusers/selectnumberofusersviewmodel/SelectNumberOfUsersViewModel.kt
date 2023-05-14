package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.content.Context
import android.content.Intent
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import com.example.randomuserapi.utils.IntentExtrasName

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    private var logTag = this::class.java.toString()
    private lateinit var randomUserUseCase: RandomUserUseCase

    fun initializeViewModel(){
        randomUserUseCase = RandomUserUseCase()
    }

    override fun navigateToListOfUsers(context: Context, numberOfUsers: Int) {
        val intent = Intent(context, ListOfUsersActivity::class.java)
        intent.putExtra(IntentExtrasName.numberOfUsers, numberOfUsers)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}