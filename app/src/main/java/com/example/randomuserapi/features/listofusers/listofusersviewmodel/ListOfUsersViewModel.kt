package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.utils.TransformEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListOfUsersViewModel: ViewModel(), ListOfUsersViewModelInterface {

    private var TAG = this.javaClass.name
    private lateinit var randomUserUseCase: RandomUserUseCase
    private var userRecievedFromApiCall: RandomUser? = null
    private var userListPrepared: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var listOfUsers: ArrayList<RandomUser>

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
        this.userListPrepared.value = false
        this.listOfUsers = ArrayList()
    }

    override fun randomUserApiCall(numberOfUsers: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val retrofitInstance = randomUserUseCase.retrofitBuilderRandomUserInstance()

            for(position in 0 until numberOfUsers) {

                val response = retrofitInstance?.getRandomUserDataCall()

                if (response != null) {
                    if(response.isSuccessful){

                        Log.d(TAG,"INFO - Success Body -> "+response.body())
                        Log.d(TAG,"INFO - Success Code -> "+response.code())

                        userRecievedFromApiCall = response.body()?.let { TransformEntity.fromEntityToUser(it) }

                        userRecievedFromApiCall?.let { listOfUsers.add(it) }

                        if(position+1 == numberOfUsers){
                            userListPrepared.postValue(true)
                        }

                    } else {
                        Log.e(TAG,"INFO - Error Body -> "+response.body())
                        Log.e(TAG,"INFO - Error Code -> "+response.code())

                    }
                }

            }

        }

    }

    override fun getRandomUserList(): ArrayList<RandomUser> {
        return this.listOfUsers
    }

    override fun getUserListPreparedValue(): MutableLiveData <Boolean> {
        return this.userListPrepared
    }

}