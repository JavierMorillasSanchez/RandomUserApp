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

    var logTag = this::class.java.toString()

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

    suspend fun getRandomUsersFromCall(): RandomUserEntity? {
       return retrofitBuilderRandomUserInstance()?.getRandomUserDataCall()
    }

    suspend fun fetchRandomUserDataFromApi(){
        RandomUserUseCase().retrofitBuilderRandomUserInstance()?.getUserFromRandomUserApi()
            ?.enqueue(object: Callback<RandomUserEntity> {
                override fun onResponse(
                    call: Call<RandomUserEntity>,
                    response: Response<RandomUserEntity>
                ) {
                    if(response.isSuccessful){
                        fromJsonToRandomUserObject(response)
                        println( "<-- ${response.code()}: ${fromJsonToRandomUserObject(response)}")
                    }
                }

                override fun onFailure(call: Call<RandomUserEntity>, t: Throwable) {
                    println("<-- ${t.message}: ${t.cause}")
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

}