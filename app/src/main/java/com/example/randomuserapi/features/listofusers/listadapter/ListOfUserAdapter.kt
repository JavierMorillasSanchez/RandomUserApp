package com.example.randomuserapi.features.listofusers.listadapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.features.userdetail.UserDetailFragment
import com.example.randomuserapi.utils.IntentExtrasName
import com.example.randomuserapi.utils.render

class ListOfUserAdapter (
    private val randomUserList: ArrayList<RandomUser>
): RecyclerView.Adapter<RandomUserViewHolder>() {

    lateinit var userDetailFragment: UserDetailFragment


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.random_user_list_cell, parent, false)
        return RandomUserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return randomUserList.size
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {

        holder.cellUserId.text = (position+1).toString()
        holder.cellUserName.text = randomUserList[position].getRandomUserFullName()
        holder.cellUserPhone.text = randomUserList[position].phone
        holder.cellUserMail.text = randomUserList[position].email
        holder.cellImg.render(randomUserList[position].pictureThumbnail, holder.itemView.context)

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val detailFragment = UserDetailFragment(randomUserList[position])

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.frag_user_detail, detailFragment)
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter_down_to_up, R.anim.exit_up_to_down)
                .commit()
        }

    }

}