package com.example.randomuserapi.calls.data

import com.example.randomuserapi.calls.data.entities.RandomUserEntity

class RandomUserProvider {
    companion object{
        var randomUser: List<RandomUserEntity> = emptyList()
    }
}