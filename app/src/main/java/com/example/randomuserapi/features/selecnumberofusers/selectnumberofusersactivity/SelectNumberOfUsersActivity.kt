package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase

class SelectNumberOfUsersActivity : AppCompatActivity() {

    var LOG_TAG = "SelectNumberOfUsersActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_select_number_of_users.xml`)


    }

    private fun callRandomUserUseCase(){
        RandomUserUseCase().fetchCurrencyData().start()
    }

}