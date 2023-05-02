package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCaseRetrofit
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserResultsEntity
import com.example.randomuserapi.databinding.ActivitySelectNumberOfUsersBinding
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

    var logTag = this::class.java.toString()
    lateinit var viewmodel: SelectNumberOfUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_number_of_users)

        this.viewmodel = SelectNumberOfUsersViewModel()
        this.viewmodel.callRandomUserUseCaseRetrofit()
    }

}

