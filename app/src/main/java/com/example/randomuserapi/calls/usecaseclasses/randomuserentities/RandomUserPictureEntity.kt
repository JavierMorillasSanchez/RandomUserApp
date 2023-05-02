package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

data class RandomUserPictureEntity(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)