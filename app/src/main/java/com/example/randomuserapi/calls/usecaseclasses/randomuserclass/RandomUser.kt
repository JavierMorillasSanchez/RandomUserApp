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
    fun transform(randomUserResultsEntity: RandomUserResultsEntity): RandomUser{
        return RandomUser(
            randomUserResultsEntity.name?.title,
            randomUserResultsEntity.name?.firstName,
            randomUserResultsEntity.name?.lastName,
            randomUserResultsEntity.picture?.large,
            randomUserResultsEntity.picture?.medium,
            randomUserResultsEntity.picture?.thumbnail,
            randomUserResultsEntity.gender,
            randomUserResultsEntity.email,
            randomUserResultsEntity.phone
        )
    }
}