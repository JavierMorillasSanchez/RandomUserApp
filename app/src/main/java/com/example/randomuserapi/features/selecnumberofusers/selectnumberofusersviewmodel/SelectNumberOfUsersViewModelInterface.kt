package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.content.Context

interface SelectNumberOfUsersViewModelInterface {
    fun navigateToListOfUsers(context: Context, numberOfUsers: Int)
}