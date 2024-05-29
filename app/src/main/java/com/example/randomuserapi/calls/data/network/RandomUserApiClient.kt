package com.example.randomuserapi.calls.data.network

import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.utils.ApiEndpoint
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApiClient {
    @GET(ApiEndpoint.RANDOM_USER_API_ENDPOINT)
    suspend fun getRandomUserDataCall(): Response<RandomUserModel>
}