package com.example.randomuserapi.features.userdetail

import android.content.Context
import android.widget.ImageView

interface UserDetailFragmentInterface {
    fun setUserInfo()
    fun loadImage(url: String?, userImg: ImageView, context: Context)
    fun initializeUI()
    fun closeDetail()
}