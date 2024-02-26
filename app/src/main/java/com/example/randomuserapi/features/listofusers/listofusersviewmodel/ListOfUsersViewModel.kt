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

    private lateinit var randomUserUseCase: RandomUserUseCase
    private var userRecievedFromApiCall: RandomUser? = null
    private var userListPrepared: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var listOfUsers: ArrayList<RandomUser>

    override fun initializeViewModel(){
        this.randomUserUseCase = RandomUserUseCase()
        this.userListPrepared.value = false
        this.listOfUsers = ArrayList()
    }

    override fun randomUserCall(numberOfUsers: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val retrofitInstance = randomUserUseCase.retrofitBuilderRandomUserInstance()

            for(position in 0 until numberOfUsers) {

                val response = retrofitInstance?.getRandomUserDataCall()

                userRecievedFromApiCall = response?.let { TransformEntity.fromEntityToUser(it) }

                userRecievedFromApiCall?.let { listOfUsers.add(it) }

                if(position+1 == numberOfUsers){
                    userListPrepared.postValue(true)
                }

                Log.d("","INFO - Numero de usuarios solicitados -> "+numberOfUsers)
                Log.d("","INFO - Numero de llamadas -> "+position)
                Log.d("","INFO - User recibido -> "+userRecievedFromApiCall!!.getRandomUserFullName())

            }

        }

    }

    override fun getRandomUserList(): ArrayList<RandomUser> {
        return this.listOfUsers
    }

    override fun checkIfUserListPrepared(): MutableLiveData <Boolean> {
        return this.userListPrepared
    }

}