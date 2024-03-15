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
import com.example.randomuserapi.utils.render

class UserDetailFragment(
    private val randomUserInfo: RandomUser
) : Fragment(), UserDetailFragmentInterface {

    lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        initializeUI()

        return this.binding.root
    }

    override fun initializeUI(){
        this.binding.imgClose.setOnClickListener { closeDetail() }
        setUserInfo()
    }

    override fun setUserInfo (){

        context?.let {
            this.binding.imgUserImageDetail.render(
                randomUserInfo.pictureLarge,
                it
            )
        }

        this.binding.txtNameDetail.text = randomUserInfo.getRandomUserFullName()
        this.binding.txtPhoneDetail.text = randomUserInfo.phone
        this.binding.txtMailDetail.text = randomUserInfo.email
        this.binding.txtGenderDetail.text = randomUserInfo.gender
        this.binding.txtNatDetail.text = randomUserInfo.nationality
        this.binding.txtAddressDetail.text = randomUserInfo.address
        this.binding.txtNumAddressDetail.text = randomUserInfo.addressNumber
        this.binding.txtBirthdateDetail.text = randomUserInfo.birthday
        this.binding.txtAgeDetail.text = randomUserInfo.age
    }

    override fun loadImage(url: String?, userImg: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .error(R.drawable.default_user)
            .into(userImg)
    }

    override fun closeDetail() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }



}