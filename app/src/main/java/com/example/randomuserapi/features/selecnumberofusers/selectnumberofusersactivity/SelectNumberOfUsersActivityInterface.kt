package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

interface SelectNumberOfUsersActivityInterface {
    fun initializeUI()
    fun checkNumberOfUsers(numberOfUsers: Int)
    fun getNumberOfUsers(): Int
}