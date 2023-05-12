package com.example.randomuserapi.features.listofusers.listofusersactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.databinding.ActivityListOfUsersBinding
import com.example.randomuserapi.features.listofusers.listadapter.ListOfUserAdapter
import com.example.randomuserapi.features.listofusers.listofusersviewmodel.ListOfUsersViewModel
import kotlinx.coroutines.*

class ListOfUsersActivity : AppCompatActivity(), ListOfUsersActivityInterface {

    private lateinit var binding: ActivityListOfUsersBinding
    private lateinit var viewModel: ListOfUsersViewModel

    lateinit var listOfUsersAdapter: ListOfUserAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    fun initializeUI(){
        this.viewModel = ListOfUsersViewModel()
        this.viewModel.initializeViewModel()
        this.viewModel.getUserList(5,applicationContext)
        prepareUserList()
    }

    override fun prepareUserList() {

        this.listOfUsersAdapter = ListOfUserAdapter(this.viewModel.getArrayOfUsersFromCall())

        val layoutManager = LinearLayoutManager(applicationContext)

        this.recyclerView = this.binding.rvRandomUserList
        this.recyclerView.layoutManager = layoutManager
        this.recyclerView.adapter = listOfUsersAdapter

        endLottieAndShowUserList()
    }

    override fun endLottieAndShowUserList(){
        /*
        if(randomUserArrayList.isNotEmpty()){
            this.binding.rvRandomUserList.visibility = View.VISIBLE
            this.binding.llLoadingUserList.visibility = View.GONE
        }

         */
    }

}