package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.randomuserapi.R
import com.example.randomuserapi.databinding.ActivitySelectNumberOfUsersBinding
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel
import com.example.randomuserapi.utils.CustomDialog
import com.example.randomuserapi.utils.NetworkState


class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

    var logTag = this::class.java.toString()

    private lateinit var viewmodel: SelectNumberOfUsersViewModel
    private lateinit var binding: ActivitySelectNumberOfUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivitySelectNumberOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.viewmodel = SelectNumberOfUsersViewModel()
        this.viewmodel.initializeViewModel()
        initializeUI()
    }

    override fun initializeUI() {
        this.binding.btnShowUsers.setOnClickListener {
            checkNetworkStateToShowUsers()
        }
    }

    override fun getNumberOfUsers(): Int {
        if(this.binding.etxtIntroduceNumberOfUsers.text.toString().isEmpty()){
            return 0
        }
        return Integer.parseInt(this.binding.etxtIntroduceNumberOfUsers.text.toString())
    }

    override fun checkNumberOfUsers(numberOfUsers: Int) = when {
            numberOfUsers == 0 -> Toast.makeText(applicationContext, R.string.introduce_number_of_users_to_show, Toast.LENGTH_SHORT).show()
            numberOfUsers >= 51 -> Toast.makeText(applicationContext, R.string.too_many_users, Toast.LENGTH_SHORT).show()
            else -> this.viewmodel.navigateToListOfUsers(applicationContext, getNumberOfUsers())
    }

    override fun checkNetworkStateToShowUsers(){
        if(NetworkState.isOnline(applicationContext, logTag)){
            checkNumberOfUsers(getNumberOfUsers())
        } else {
            CustomDialog.infoDialog(
                this,
                resources.getString(R.string.dialog_title_no_internet),
                resources.getString(R.string.dialog_message_no_internet))
        }
    }

}



