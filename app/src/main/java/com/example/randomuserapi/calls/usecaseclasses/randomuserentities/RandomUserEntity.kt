package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

data class RandomUserEntity(
    @SerializedName("results")
    val randomUserResultsEntity: ArrayList<RandomUserResultsEntity>? = null
)