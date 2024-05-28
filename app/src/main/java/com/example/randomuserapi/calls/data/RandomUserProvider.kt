package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.model.RandomUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomUserProvider @Inject constructor(){
    var randomUser: List<RandomUserModel> = emptyList()
}