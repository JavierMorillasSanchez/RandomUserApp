package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuserapi.R
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel

class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

    var logTag = this::class.java.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_number_of_users)
        var intent = Intent(this, ListOfUsersActivity::class.java)
        startActivity(intent)
    }

}

