package com.example.randomuserapi.calls.data.model

import com.google.gson.annotations.SerializedName

data class DobModel(
    @SerializedName("date")
    val birthdate: String?,
    @SerializedName("age")
    val age: String?
){
    fun getBirthdateFormatted(): String? {
        val timePosition = birthdate?.lastIndexOf("T")
        val formattedBirthdate = timePosition?.let { birthdate?.substring(0, it) }
        return formattedBirthdate
    }
}
