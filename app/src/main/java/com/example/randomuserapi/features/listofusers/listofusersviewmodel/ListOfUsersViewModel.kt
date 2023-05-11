package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.util.Log
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListOfUsersViewModel: ListOfUsersViewModelInterface {

    var logTag = this::class.java.toString()

    private lateinit var randomUserUseCase: RandomUserUseCase
    lateinit var randomUserArray: ArrayList<RandomUser>

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
        this.randomUserArray = ArrayList()
    }

    override fun populateRandomUserArray(numberOfUsers: Int){
        for(call in 1..numberOfUsers){
            this.randomUserUseCase.retrofitBuilderRandomUserData()?.getUserFromRandomUserApi()
                ?.enqueue(object: Callback<RandomUserEntity> {
                    override fun onResponse(
                        call: Call<RandomUserEntity>,
                        response: Response<RandomUserEntity>
                    ) {
                        if(response.isSuccessful){
                            randomUserArray.add(randomUserUseCase.fromJsonToRandomUserObject(response))
                            Log.d(logTag, "<-- ${response.code()}: ${randomUserUseCase.fromJsonToRandomUserObject(response)}")
                        }
                    }

                    override fun onFailure(call: Call<RandomUserEntity>, t: Throwable) {
                        Log.d(logTag, "<-- ${t.message}: ${t.cause}")
                    }

                })
        }
    }
    
}