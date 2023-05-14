package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity

interface ListOfUsersViewModelInterface {
    fun fromEntityToUser(userEntity: RandomUserEntity): RandomUser
    fun initializeViewModel()
}