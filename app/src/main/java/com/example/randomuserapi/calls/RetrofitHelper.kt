package com.example.randomuserapi.calls

import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiUrl.randomUserApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}