package com.example.randomuserapi.features.listofusers.listofusersactivity

interface ListOfUsersActivityInterface {
    suspend fun prepareUserList()
    fun getUserList(numberOfUsers: Int)
    fun endLottieAndShowUserList()
}