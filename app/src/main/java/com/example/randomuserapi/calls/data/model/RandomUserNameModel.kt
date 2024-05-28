package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserNameModel(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("first")
    val firstName: String? = null,
    @SerializedName("last")
    val lastName: String? = null
)