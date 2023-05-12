package com.example.randomuserapi.calls.usecaseclasses.randomuserclass

import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserResultsEntity

data class RandomUser(
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val pictureLarge: String?,
    val pictureMedium: String?,
    val pictureThumbnail: String?,
    val gender: String?,
    val email: String?,
    val phone: String?,
) {
    fun getRandomUserFullName(): String{
        return "$title $firstName $lastName"
    }
}