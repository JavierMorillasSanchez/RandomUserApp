package com.example.randomuserapi.calls.data.entities

import com.google.gson.annotations.SerializedName

data class RandomUserResultsEntity(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: RandomUserNameEntity?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("picture")
    val picture: RandomUserPictureEntity?,
    @SerializedName("nat")
    val nationality: String?,
    @SerializedName("location")
    val location: LocationEntity?,
    @SerializedName("dob")
    val dob: DobEntity?
)