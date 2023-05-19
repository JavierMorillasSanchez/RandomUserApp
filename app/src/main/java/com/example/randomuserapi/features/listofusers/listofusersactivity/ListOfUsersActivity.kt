package com.example.randomuserapi.features.listofusers.listofusersactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.databinding.ActivityListOfUsersBinding
import com.example.randomuserapi.features.listofusers.listadapter.ListOfUserAdapter
import com.example.randomuserapi.features.listofusers.listofusersviewmodel.ListOfUsersViewModel
import com.example.randomuserapi.utils.FilterFunctions
import com.example.randomuserapi.utils.IntentExtrasName
import com.example.randomuserapi.utils.TransformEntity
import kotlinx.coroutines.*

class ListOfUsersActivity : AppCompatActivity(), ListOfUsersActivityInterface {

    private lateinit var binding: ActivityListOfUsersBinding
    private lateinit var viewModel: ListOfUsersViewModel

    lateinit var listOfUsersAdapter: ListOfUserAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LayoutManager

    lateinit var arrayOfUsers: ArrayList<RandomUser>
    lateinit var filteredArray: ArrayList<RandomUser>
    var numberOfUsersToShow: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    override fun onBackPressed() {
        finish()
    }

    fun initializeUI(){

        this.numberOfUsersToShow = intent.extras?.getInt(IntentExtrasName.numberOfUsers)

        this.viewModel = ListOfUsersViewModel()
        this.viewModel.initializeViewModel()
        numberOfUsersToShow?.let { this.getUserList(it,applicationContext) }
        this.arrayOfUsers = ArrayList()

        this.binding.optionAllUsers.setOnClickListener {
            prepareUserList(arrayOfUsers)
        }

        this.binding.optionMale.setOnClickListener {
            filterMaleUserList()
        }

        this.binding.optioFemale.setOnClickListener {
            filterFemaleUserList()
        }

    }

    override fun prepareUserList(userArrayList: ArrayList<RandomUser>) {

        this.listOfUsersAdapter = ListOfUserAdapter(userArrayList)

        this.layoutManager = LinearLayoutManager(applicationContext)

        this.recyclerView = this.binding.rvRandomUserList
        this.recyclerView.layoutManager = layoutManager
        this.recyclerView.adapter = listOfUsersAdapter

        endLottieAndShowUserList()
    }

    override fun endLottieAndShowUserList(){
        if(arrayOfUsers.isNotEmpty()){
            this.binding.llUserList.visibility = View.VISIBLE
            this.binding.llLoadingUserList.visibility = View.GONE
        }
    }

    override fun getUserList(numberOfUsers: Int, context: Context) {

        val fetchRandomUserData = Job()

        val errorHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
            println("Error ---> ${throwable.message}")
            showErrorWhileLoadingUsers()
        }

        val scope = CoroutineScope(fetchRandomUserData + Dispatchers.Main)

        scope.launch(errorHandler){
            var randomUser = viewModel.randomUserUseCase.getRandomUsersFromCall()
            if(randomUser != null){
                arrayOfUsers.add(TransformEntity.fromEntityToUser(randomUser))
                if(arrayOfUsers.size < numberOfUsers){
                    getUserList(numberOfUsers,context)
                } else {
                    prepareUserList(arrayOfUsers)
                }
            }
        }

    }

    override fun showErrorWhileLoadingUsers(){
        this.binding.llError.visibility = View.VISIBLE
        this.binding.rvRandomUserList.visibility = View.GONE
        this.binding.llLoadingUserList.visibility = View.GONE
        this.binding.btnErrorGoBack.setOnClickListener { finish() }
    }

    override fun filterMaleUserList(){
        this.filteredArray = ArrayList()
        for(user in arrayOfUsers){
            if(FilterFunctions.userIsMale(user)){
                filteredArray.add(user)
            }
        }
        prepareUserList(filteredArray)
    }

    override fun filterFemaleUserList(){
        this.filteredArray = ArrayList()
        for(user in arrayOfUsers){
            if(!FilterFunctions.userIsMale(user)){
                filteredArray.add(user)
            }
        }
        prepareUserList(filteredArray)
    }

}