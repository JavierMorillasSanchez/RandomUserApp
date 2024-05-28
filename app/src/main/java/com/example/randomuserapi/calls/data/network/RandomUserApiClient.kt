package com.example.randomuserapi.calls.data.network

import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApiClient {
    @GET(ApiUrl.randomUserApiUrl)
    suspend fun getRandomUserDataCall(): Response<RandomUserModel>
}