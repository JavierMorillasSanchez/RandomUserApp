package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.randomuserapi.databinding.ActivitySelectNumberOfUsersBinding
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel

class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

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
            this.viewmodel.checkNetworkStateToShowUsers(this, getNumberOfUsers())
        }
    }

    override fun getNumberOfUsers(): Int {

        var numberOfUsersInput = this.binding.etxtIntroduceNumberOfUsers.text.toString()

        try {
            return Integer.parseInt(numberOfUsersInput)
        } catch (e:Exception){
            return 0
        }
    }

}



