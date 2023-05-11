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

    var randomUserUseCase: RandomUserUseCase = RandomUserUseCase()

    lateinit var listOfUsersAdapter: ListOfUserAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var randomUserArrayList: ArrayList<RandomUser>

    init {
        getUserList(5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    fun initializeUI(){

        this.randomUserArrayList = ArrayList()

        /*
        this.viewModel = ListOfUsersViewModel()
        this.viewModel.initializeViewModel()
        this.viewModel.populateRandomUserArray(3)

         */

    }

    override fun getUserList(numberOfUsers: Int) {

        val fetchRandomUserData = Job()

        val errorHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
            Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_LONG).show()
            getUserList(1)
        }

        val scope = CoroutineScope(fetchRandomUserData + Dispatchers.Main)

        for(calls in 1..numberOfUsers){

            scope.launch(errorHandler){
                var randomUser = randomUserUseCase.getRandomUsersFromCall()
                if(randomUser != null){
                    randomUserArrayList.add(fromEntityToUser(randomUser))
                    println("Data obtained from ${randomUserArrayList} users.")
                }
                prepareUserList()
            }

        }

    }

    fun fromEntityToUser(userEntity: RandomUserEntity): RandomUser {
        return RandomUser(
            userEntity.randomUserResultsEntity?.get(0)?.name?.title,
            userEntity.randomUserResultsEntity?.get(0)?.name?.firstName,
            userEntity.randomUserResultsEntity?.get(0)?.name?.lastName,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.large,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.medium,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.thumbnail,
            userEntity.randomUserResultsEntity?.get(0)?.gender,
            userEntity.randomUserResultsEntity?.get(0)?.email,
            userEntity.randomUserResultsEntity?.get(0)?.phone
        )
    }


    override suspend fun prepareUserList() {

        this.listOfUsersAdapter = ListOfUserAdapter(this.randomUserArrayList)

        val layoutManager = LinearLayoutManager(applicationContext)

        this.recyclerView = this.binding.rvRandomUserList
        this.recyclerView.layoutManager = layoutManager
        this.recyclerView.adapter = listOfUsersAdapter

        endLottieAndShowUserList()
    }

    override fun endLottieAndShowUserList(){
        if(randomUserArrayList.isNotEmpty()){
            this.binding.rvRandomUserList.visibility = View.VISIBLE
            this.binding.llLoadingUserList.visibility = View.GONE
        }
    }

}