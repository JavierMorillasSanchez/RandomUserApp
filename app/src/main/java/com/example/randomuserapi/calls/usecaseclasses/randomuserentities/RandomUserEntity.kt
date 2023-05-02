package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

class RandomUserEntity(
    @SerializedName("results")
    val randomUserResultsEntity: ArrayList<RandomUserResultsEntity>? = null
)