package com.example.randomuserapi.features.listofusers.listofusersactivity

import android.content.Context
import com.example.randomuserapi.calls.domain.model.RandomUser

interface ListOfUsersActivityInterface {
    fun initializeUI()
    fun prepareUserList(userArrayList: ArrayList<RandomUser>)
    fun endLottieAndShowUserList()
    fun getUserList(numberOfUsers: Int, context: Context)
    fun showErrorWhileLoadingUsers()
    fun filterMaleUserList()
    fun filterFemaleUserList()

}