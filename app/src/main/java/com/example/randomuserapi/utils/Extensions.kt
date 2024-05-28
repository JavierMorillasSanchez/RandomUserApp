package com.example.randomuserapi.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.data.model.RandomUserModel
import com.example.randomuserapi.calls.domain.model.RandomUser

fun ImageView.render(url: String?, context: Context) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.default_user)
        .into(this)
}