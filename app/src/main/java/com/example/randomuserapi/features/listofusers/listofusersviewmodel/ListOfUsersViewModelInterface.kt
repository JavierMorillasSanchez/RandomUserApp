package com.example.randomuserapi.features.listofusers.listofusersviewmodel

interface ListOfUsersViewModelInterface {
    fun initializeViewModel()
    suspend fun populateRandomUserArray(numberOfUsers: Int)
}