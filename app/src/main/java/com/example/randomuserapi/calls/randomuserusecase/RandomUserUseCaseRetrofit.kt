package com.example.randomuserapi.calls.randomuserusecase

import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserUseCaseRetrofit {

    val LOG_TAG = "RandomUserUseCaseRetrofit"

    private var retrofit: Retrofit? = null

    fun fetchRandomUserDataRetrofit(): RandomUserUseCaseInterface? {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.randomUserApiUrlRetrofit)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit?.create(RandomUserUseCaseInterface::class.java)

    }

}