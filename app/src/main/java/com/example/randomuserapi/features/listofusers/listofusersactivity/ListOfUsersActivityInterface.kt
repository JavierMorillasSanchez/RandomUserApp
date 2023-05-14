package com.example.randomuserapi.features.listofusers.listofusersactivity

import android.content.Context

interface ListOfUsersActivityInterface {
    fun prepareUserList()
    fun endLottieAndShowUserList()
    fun getUserList(numberOfUsers: Int, context: Context)

}