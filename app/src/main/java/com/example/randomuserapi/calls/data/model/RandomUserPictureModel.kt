package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserPictureModel(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)