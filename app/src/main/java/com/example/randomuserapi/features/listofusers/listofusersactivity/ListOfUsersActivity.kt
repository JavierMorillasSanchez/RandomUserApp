package com.example.randomuserapi.features.listofusers.listofusersactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.databinding.ActivityListOfUsersBinding
import com.example.randomuserapi.features.listofusers.listadapter.ListOfUserAdapter
import com.example.randomuserapi.features.listofusers.listofusersviewmodel.ListOfUsersViewModel
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
    var numberOfUsersToShow: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    fun initializeUI(){

        this.numberOfUsersToShow = intent.extras?.getInt(IntentExtrasName.numberOfUsers)

        this.viewModel = ListOfUsersViewModel()
        this.viewModel.initializeViewModel()
        numberOfUsersToShow?.let { this.getUserList(it,applicationContext) }
        this.arrayOfUsers = ArrayList()
    }

    override fun prepareUserList() {

        this.listOfUsersAdapter = ListOfUserAdapter(arrayOfUsers)

        this.layoutManager = LinearLayoutManager(applicationContext)

        this.recyclerView = this.binding.rvRandomUserList
        this.recyclerView.layoutManager = layoutManager
        this.recyclerView.adapter = listOfUsersAdapter

        endLottieAndShowUserList()
    }

    override fun endLottieAndShowUserList(){
        if(arrayOfUsers.isNotEmpty()){
            this.binding.rvRandomUserList.visibility = View.VISIBLE
            this.binding.llLoadingUserList.visibility = View.GONE
        }
    }

    override fun getUserList(numberOfUsers: Int, context: Context) {

        val fetchRandomUserData = Job()

        val errorHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
            Toast.makeText(context, "${throwable.message}: Ha ocurrido un error y no se pueden obtener más usuarios.", Toast.LENGTH_LONG).show()
            println("Error ---> ${throwable.message}")
        }

        val scope = CoroutineScope(fetchRandomUserData + Dispatchers.Main)

        scope.launch(errorHandler){
            var randomUser = viewModel.randomUserUseCase.getRandomUsersFromCall()
            if(randomUser != null){
                arrayOfUsers.add(TransformEntity.fromEntityToUser(randomUser))
                if(arrayOfUsers.size < numberOfUsers){
                    getUserList(numberOfUsers,context)
                } else {
                    prepareUserList()
                }
            }
        }

    }

}