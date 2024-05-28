package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserResultsModel(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: RandomUserNameModel?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("picture")
    val picture: RandomUserPictureModel?,
    @SerializedName("nat")
    val nationality: String?,
    @SerializedName("location")
    val location: LocationModel?,
    @SerializedName("dob")
    val dob: DobModel?
)