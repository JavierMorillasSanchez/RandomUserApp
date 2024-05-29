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
        initializeUI()
    }

    override fun initializeUI() {
        this.binding.btnShowUsersFromApi.setOnClickListener {
            this.viewmodel.checkNetworkStateToShowUsers(this, getNumberOfUsers())
        }

        this.binding.btnShowUsersFromDatabase.setOnClickListener {
            this.viewmodel.navigateToListOfUsersAndGetListFromDatabase(this)
        }
    }

    override fun getNumberOfUsers(): Int {

        val numberOfUsersInput = this.binding.etxtIntroduceNumberOfUsers.text.toString()

        return try {
            Integer.parseInt(numberOfUsersInput)
        } catch (e:Exception){
            0
        }
    }

}



