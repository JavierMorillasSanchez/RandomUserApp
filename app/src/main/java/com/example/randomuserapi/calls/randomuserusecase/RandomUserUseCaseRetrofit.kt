package com.example.randomuserapi.calls.randomuserusecase

import android.util.Log
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserUseCaseRetrofit {

    var logTag = this::class.java.toString()

    private var retrofit: Retrofit? = null

    fun retrofitBuilderRandomUserDataRetrofit(): RandomUserUseCaseInterface? {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.randomUserApiUrlRetrofit)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit?.create(RandomUserUseCaseInterface::class.java)

    }

    fun fetchRandomUserDataRetrofit(){
        RandomUserUseCaseRetrofit().retrofitBuilderRandomUserDataRetrofit()?.getUserFromRandomUserApi()
            ?.enqueue(object: Callback<RandomUserEntity> {
                override fun onResponse(
                    call: Call<RandomUserEntity>,
                    response: Response<RandomUserEntity>
                ) {
                    if(response.isSuccessful){
                        Log.d(logTag, "Obtained data using Retrofit from ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<RandomUserEntity>, t: Throwable) {
                    Log.d(logTag, t.message.toString())
                }

            })
    }

}