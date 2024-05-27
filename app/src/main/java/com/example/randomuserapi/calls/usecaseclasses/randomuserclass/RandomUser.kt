package com.example.randomuserapi.calls.usecaseclasses.randomuserclass

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
    val nationality: String?,
    val address: String?,
    val addressNumber: String?,
    val birthday: String?,
    val age: String?,
) {
    fun getRandomUserFullName(): String{
        return "$title $firstName $lastName"
    }
}