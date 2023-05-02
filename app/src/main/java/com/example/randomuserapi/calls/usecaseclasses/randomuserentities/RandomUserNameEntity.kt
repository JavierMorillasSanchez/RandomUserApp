package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

class RandomUserNameEntity(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("first")
    val firstName: String? = null,
    @SerializedName("last")
    val lastName: String? = null
)