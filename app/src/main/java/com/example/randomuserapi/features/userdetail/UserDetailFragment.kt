package com.example.randomuserapi.features.userdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.randomuserapi.R
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.databinding.FragmentUserDetailBinding

class UserDetailFragment(
    private val randomUserInfo: RandomUser
) : Fragment(), UserDetailFragmentInterface {

    lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        setUserInfo()

        return this.binding.root
    }

    override fun setUserInfo (){

        context?.let { loadImage(randomUserInfo.pictureLarge, this.binding.imgUserImageDetail, it.applicationContext) }

        this.binding.txtNameDetail.text = randomUserInfo.getRandomUserFullName()
        this.binding.txtPhoneDetail.text = randomUserInfo.phone
        this.binding.txtMailDetail.text = randomUserInfo.email
        this.binding.txtNatDetail.text = randomUserInfo.nationality
        this.binding.txtAddressDetail.text
        this.binding.txtNumAddressDetail.text
        this.binding.txtBirthdateDetail.text
        this.binding.txtAgeDetail.text
        this.binding.txtNicknameDetail.text




    }

    override fun loadImage(url: String?, userImg: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .error(R.drawable.default_user)
            .into(userImg)
    }




}