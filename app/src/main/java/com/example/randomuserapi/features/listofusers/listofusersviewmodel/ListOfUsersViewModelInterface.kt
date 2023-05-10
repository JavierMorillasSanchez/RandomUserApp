package com.example.randomuserapi.features.listofusers.listofusersviewmodel

interface ListOfUsersViewModelInterface {
    fun initializeViewModel()
    fun populateRandomUserArray(numberOfUsers: Int)
}