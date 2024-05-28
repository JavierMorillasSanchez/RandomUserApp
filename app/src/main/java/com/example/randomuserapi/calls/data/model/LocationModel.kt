package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class LocationModel (
    @SerializedName("street")
    val street: StreetModel?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?
)
