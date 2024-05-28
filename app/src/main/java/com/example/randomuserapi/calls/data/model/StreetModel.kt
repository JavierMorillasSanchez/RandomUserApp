package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class StreetModel(
    @SerializedName("number")
    val number: String?,
    @SerializedName("name")
    val name: String?
)