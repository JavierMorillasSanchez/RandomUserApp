package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.randomuserapi.R
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import com.example.randomuserapi.utils.InfoDialog
import com.example.randomuserapi.utils.IntentExtrasName
import com.example.randomuserapi.utils.NetworkState

class SelectNumberOfUsersViewModel: SelectNumberOfUsersViewModelInterface {

    private var logTag = this::class.java.toString()
    private var numberOfUsers = 0

    override fun navigateToListOfUsers(context: Context, numberOfUsers: Int) {
        val intent = Intent(context, ListOfUsersActivity::class.java)
        intent.putExtra(IntentExtrasName.numberOfUsers, numberOfUsers)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun checkNetworkStateToShowUsers(context: Context, numberOfUsers: Int){

        setNumberOfUsers(numberOfUsers)

        if(NetworkState.isOnline(context, logTag)){
            checkNumberOfUsers(context, numberOfUsers)
        } else {
            InfoDialog.createDialog().customDialog(context)
        }
    }

    override fun checkNumberOfUsers(context: Context, numberOfUsers: Int) = when {
        numberOfUsers == 0 -> Toast.makeText(context, R.string.toast_introduce_number_of_users_to_show, Toast.LENGTH_SHORT).show()
        numberOfUsers >= 51 -> Toast.makeText(context, R.string.toast_too_many_users, Toast.LENGTH_SHORT).show()
        else -> navigateToListOfUsers(context, numberOfUsers)
    }

    override fun setNumberOfUsers(numberOfUsers: Int) {
        this.numberOfUsers = numberOfUsers
    }

}