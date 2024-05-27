package com.example.randomuserapi.calls.data.entities

import com.google.gson.annotations.SerializedName

data class RandomUserNameEntity(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("first")
    val firstName: String? = null,
    @SerializedName("last")
    val lastName: String? = null
)