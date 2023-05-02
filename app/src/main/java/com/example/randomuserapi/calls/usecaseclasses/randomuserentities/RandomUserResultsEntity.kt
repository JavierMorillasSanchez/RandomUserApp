package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

class RandomUserResultsEntity(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: RandomUserNameEntity?
)