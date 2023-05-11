package com.example.randomuserapi.features.listofusers.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser

class ListOfUserAdapter (
    private val randomUserList: ArrayList<RandomUser>
): RecyclerView.Adapter<RandomUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.random_user_list_cell, parent, false)
        return RandomUserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return randomUserList.size
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {

        holder.cellUserId.text = (position+1).toString()
        holder.cellUserName.text = randomUserList[position].firstName
        holder.cellUserPhone.text = randomUserList[position].phone
        holder.cellUserMail.text = randomUserList[position].email

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Has pulsado a ${holder.cellUserName.text}", Toast.LENGTH_SHORT).show()
        }

    }

}