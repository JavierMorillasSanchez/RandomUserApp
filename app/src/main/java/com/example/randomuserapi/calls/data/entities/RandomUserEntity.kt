package com.example.randomuserapi.calls.data.entities

import com.google.gson.annotations.SerializedName

data class RandomUserEntity(
    @SerializedName("results")
    val randomUserResultsEntity: ArrayList<RandomUserResultsEntity>? = null
)
