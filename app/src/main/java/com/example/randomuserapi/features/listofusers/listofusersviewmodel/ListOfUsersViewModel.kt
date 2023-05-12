package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import android.widget.Toast
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random

class ListOfUsersViewModel: ListOfUsersViewModelInterface {

    var logTag = this::class.java.toString()

    private lateinit var randomUserUseCase: RandomUserUseCase
    private lateinit var viewModelArrayOfUsers: ArrayList<RandomUser>

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
        this.viewModelArrayOfUsers = ArrayList()
    }

    override fun getUserList(numberOfUsers: Int, context: Context) {

        for(calls in 1..numberOfUsers){

            val fetchRandomUserData = Job()

            val errorHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
                Toast.makeText(context, "${throwable.message}: Ha ocurrido un error y no se pueden obtener más usuarios.", Toast.LENGTH_LONG).show()
                println("Error ---> ${throwable.message}")
                viewModelArrayOfUsers.add(RandomUser(
                    "Ms",
                    "Jhon",
                    "Doe",
                    "",
                    "",
                    "",
                    "male",
                    "jhondoe@falsemail.com",
                    "0000-000~${viewModelArrayOfUsers.size}"
                ))
            }

            val scope = CoroutineScope(fetchRandomUserData + Dispatchers.Main)

                scope.launch(errorHandler){
                    var randomUser = randomUserUseCase.getRandomUsersFromCall()
                    if(randomUser != null){
                        viewModelArrayOfUsers.add(fromEntityToUser(randomUser))
                        println("Data obtained from ${viewModelArrayOfUsers.size} users.")
                    }
                }
        }

    }

    override fun fromEntityToUser(userEntity: RandomUserEntity): RandomUser {
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

    override fun getArrayOfUsersFromCall(): ArrayList<RandomUser>{
        return this.viewModelArrayOfUsers
    }

}