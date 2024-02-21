package com.example.randomuserapi.calls.randomuserusecase

import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserUseCase {

    private var retrofit: Retrofit? = null

    private lateinit var randomUserInterface: RandomUserUseCaseInterface

    init {
        retrofitBuilderRandomUserInstance()
    }

    fun retrofitBuilderRandomUserInstance(): RandomUserUseCaseInterface? {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.randomUserApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        randomUserInterface = retrofit?.create(RandomUserUseCaseInterface::class.java)!!

        return randomUserInterface
    }

}