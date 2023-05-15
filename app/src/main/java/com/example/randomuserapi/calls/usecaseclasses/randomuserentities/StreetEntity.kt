package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

data class StreetEntity(
    @SerializedName("number")
    val number: String?,
    @SerializedName("name")
    val name: String?
)