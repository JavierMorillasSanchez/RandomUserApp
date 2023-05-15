package com.example.randomuserapi.features.listofusers.listadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapi.R

class RandomUserViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    var cellUserId: TextView = itemView.findViewById(R.id.txt_id_cell)
    var cellUserName: TextView = itemView.findViewById(R.id.txt_name_cell)
    var cellUserPhone: TextView = itemView.findViewById(R.id.txt_phone_cell)
    var cellUserMail: TextView =  itemView.findViewById(R.id.txt_mail_cell)
    var cellImg: ImageView =  itemView.findViewById(R.id.img_user_cell)

}