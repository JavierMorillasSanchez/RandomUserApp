package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserModel(
    @SerializedName("results")
    val randomUserResultsModel: ArrayList<RandomUserResultsModel>? = null
)
