package com.example.randomuserapi.features.listofusers.listofusersactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.data.RandomUser
import com.example.randomuserapi.databinding.ActivityListOfUsersBinding
import com.example.randomuserapi.features.listofusers.listadapter.ListOfUserAdapter
import com.example.randomuserapi.features.listofusers.listofusersviewmodel.ListOfUsersViewModel
import com.example.randomuserapi.utils.FilterFunctions
import com.example.randomuserapi.utils.IntentExtrasName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfUsersActivity : AppCompatActivity(), ListOfUsersActivityInterface {

    private lateinit var binding: ActivityListOfUsersBinding
    private val viewModel: ListOfUsersViewModel by viewModels()

    private lateinit var listOfUsersAdapter: ListOfUserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LayoutManager

    private lateinit var arrayOfUsers: ArrayList<RandomUser>
    private lateinit var filteredArray: ArrayList<RandomUser>
    private var numberOfUsersToShow: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initializeUI()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun initializeUI(){

        this.numberOfUsersToShow = intent.extras!!.getInt(IntentExtrasName.numberOfUsers)

        this.viewModel.initializeViewModel()
        numberOfUsersToShow?.let { this.getUserList(it,applicationContext) }
        this.arrayOfUsers = ArrayList()

        this.binding.optionMale.setOnClickListener {
            if(this.binding.optionMale.isChecked){
                filterMaleUserList()
            } else {
                prepareUserList(arrayOfUsers)
            }
        }

        this.binding.optionFemale.setOnClickListener {
            if(this.binding.optionFemale.isChecked){
                filterFemaleUserList()
            } else {
                prepareUserList(arrayOfUsers)
            }
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
            this.binding.llFilter.visibility = View.VISIBLE
            this.binding.separator.visibility = View.VISIBLE
            this.binding.llLoadingUserList.visibility = View.GONE
        }
    }

    override fun getUserList(numberOfUsers: Int, context: Context) {

        this.viewModel.randomUserApiCall(numberOfUsers)

        viewModel.getUserListPreparedValue().observe(this, Observer<Boolean> {
            if(it != null){
                arrayOfUsers.addAll(this.viewModel.getRandomUserList())
                prepareUserList(arrayOfUsers)
                if(it && arrayOfUsers.size != numberOfUsers){
                    Toast.makeText(this, R.string.toast_not_all_users, Toast.LENGTH_SHORT).show()
                }
            }

        })
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
        Toast.makeText(applicationContext, R.string.toast_showing_male_users, Toast.LENGTH_SHORT).show()
        prepareUserList(filteredArray)
    }

    override fun filterFemaleUserList(){
        this.filteredArray = ArrayList()
        for(user in arrayOfUsers){
            if(!FilterFunctions.userIsMale(user)){
                filteredArray.add(user)
            }
        }
        Toast.makeText(applicationContext, R.string.toast_showing_female_users, Toast.LENGTH_SHORT).show()
        prepareUserList(filteredArray)
    }

}