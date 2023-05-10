package com.example.randomuserapi.features.listofusers.listofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuserapi.R
import com.example.randomuserapi.databinding.ActivityListOfUsersBinding
import com.example.randomuserapi.databinding.ActivitySelectNumberOfUsersBinding
import com.example.randomuserapi.features.listofusers.listofusersviewmodel.ListOfUsersViewModel

class ListOfUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListOfUsersBinding
    private lateinit var viewModel: ListOfUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    fun initializeUI(){
        this.viewModel = ListOfUsersViewModel()
        this.viewModel.initializeViewModel()
        this.viewModel.populateRandomUserArray(3)
    }
}