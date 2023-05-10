package com.example.randomuserapi.calls.randomuserusecase

import android.util.Log
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserUseCase {

    var logTag = this::class.java.toString()

    var randomUserArray = ArrayList<RandomUser>()
    private var retrofit: Retrofit? = null

    fun retrofitBuilderRandomUserData(): RandomUserUseCaseInterface? {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.randomUserApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit?.create(RandomUserUseCaseInterface::class.java)

    }

    fun fetchRandomUserData(){
        RandomUserUseCase().retrofitBuilderRandomUserData()?.getUserFromRandomUserApi()
            ?.enqueue(object: Callback<RandomUserEntity> {
                override fun onResponse(
                    call: Call<RandomUserEntity>,
                    response: Response<RandomUserEntity>
                ) {
                    if(response.isSuccessful){
                        Log.d(logTag, "<-- ${response.code()}: ${fromJsonToRandomUserObject(response)}")
                        addRandomUserToArray(fromJsonToRandomUserObject(response))
                    }
                }

                override fun onFailure(call: Call<RandomUserEntity>, t: Throwable) {
                    Log.d(logTag, t.message.toString())
                }

            })
    }

    fun fromJsonToRandomUserObject(response: Response<RandomUserEntity>): RandomUser{
        return RandomUser(
            response.body()?.randomUserResultsEntity?.get(0)?.name?.title,
            response.body()?.randomUserResultsEntity?.get(0)?.name?.firstName,
            response.body()?.randomUserResultsEntity?.get(0)?.name?.lastName,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.large,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.medium,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.thumbnail,
            response.body()?.randomUserResultsEntity?.get(0)?.gender,
            response.body()?.randomUserResultsEntity?.get(0)?.email,
            response.body()?.randomUserResultsEntity?.get(0)?.phone
            )
    }

    fun addRandomUserToArray(randomUser: RandomUser){
        this.randomUserArray.add(randomUser)
    }

    @JvmName("getRandomUserArray1")
    fun getRandomUserArray(): ArrayList<RandomUser> { return this.randomUserArray }


}