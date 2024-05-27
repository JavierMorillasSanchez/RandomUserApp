package com.example.randomuserapi.calls.data.entities

import com.google.gson.annotations.SerializedName

data class RandomUserPictureEntity(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)