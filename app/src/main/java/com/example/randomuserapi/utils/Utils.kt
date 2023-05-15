package com.example.randomuserapi.utils

import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity

object ApiUrl{
    const val randomUserApiUrl = "https://randomuser.me/api/"
}

object IntentExtrasName{
    const val numberOfUsers = "numberOfUsers"
}

object TransformEntity{

    fun fromEntityToUser(userEntity: RandomUserEntity): RandomUser {
        return RandomUser(
            userEntity.randomUserResultsEntity?.get(0)?.name?.title,
            userEntity.randomUserResultsEntity?.get(0)?.name?.firstName,
            userEntity.randomUserResultsEntity?.get(0)?.name?.lastName,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.large,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.medium,
            userEntity.randomUserResultsEntity?.get(0)?.picture?.thumbnail,
            userEntity.randomUserResultsEntity?.get(0)?.gender,
            userEntity.randomUserResultsEntity?.get(0)?.email,
            userEntity.randomUserResultsEntity?.get(0)?.phone,
            userEntity.randomUserResultsEntity?.get(0)?.nationality,
            userEntity.randomUserResultsEntity?.get(0)?.location?.street?.name,
            userEntity.randomUserResultsEntity?.get(0)?.location?.street?.number,
            userEntity.randomUserResultsEntity?.get(0)?.dob?.getBirthdateFormatted(),
            userEntity.randomUserResultsEntity?.get(0)?.dob?.age,
        )
    }

}
