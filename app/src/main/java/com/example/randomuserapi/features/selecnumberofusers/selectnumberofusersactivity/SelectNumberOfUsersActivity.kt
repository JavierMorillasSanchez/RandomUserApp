package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCaseRetrofit
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserResultsEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SelectNumberOfUsersActivity : AppCompatActivity() {

    var LOG_TAG = "SelectNumberOfUsersActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_number_of_users)

        callRandomUserUseCaseRetrofit()

    }

    private fun callRandomUserUseCaseRetrofit(){
        //This will make a call to API using Retrofit
        RandomUserUseCaseRetrofit().fetchRandomUserDataRetrofit()?.getUserFromRandomUserApi()
            ?.enqueue(object: Callback<RandomUserEntity> {
            override fun onResponse(
                call: Call<RandomUserEntity>,
                response: Response<RandomUserEntity>
            ) {
                if(response.isSuccessful){
                    Log.d(LOG_TAG, "Obtained data using Retrofit from ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<RandomUserEntity>, t: Throwable) {
                Log.d(LOG_TAG, t.message.toString())
            }

        })
    }


    private fun callRandomUserUseCase(){
        //This will make a call to API without using Retrofit
        RandomUserUseCase().fetchRandomUserData().start()
    }

}

