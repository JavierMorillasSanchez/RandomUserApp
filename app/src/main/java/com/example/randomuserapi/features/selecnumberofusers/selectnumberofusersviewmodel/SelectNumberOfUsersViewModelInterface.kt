package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.content.Context

interface SelectNumberOfUsersViewModelInterface {
    fun navigateToListOfUsersAndGetListFromApiCall(context: Context, numberOfUsers: Int)
    fun checkNetworkStateToShowUsers(context: Context, numberOfUsers: Int)
    fun checkNumberOfUsers(context: Context, numberOfUsers: Int)
    fun setNumberOfUsers(numberOfUsers: Int)
    fun navigateToListOfUsersAndGetListFromDatabase(context: Context)
}