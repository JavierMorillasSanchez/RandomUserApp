package com.example.randomuserapi.calls.data.entities

import com.google.gson.annotations.SerializedName

data class LocationEntity (
    @SerializedName("street")
    val street: StreetEntity?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?
)
