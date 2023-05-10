package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuserapi.R
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel

class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

    var logTag = this::class.java.toString()
    lateinit var viewmodel: SelectNumberOfUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_number_of_users)

        this.viewmodel = SelectNumberOfUsersViewModel()
        this.viewmodel.initializeViewModel()
    }

}

