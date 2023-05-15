package com.example.randomuserapi.utils

import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import retrofit2.Response

object ApiUrl{
    const val randomUserApiUrl = "https://randomuser.me/api/"
}

object IntentExtrasName{
    const val numberOfUsers = "numberOfUsers"
}

object TransformEntity{
    fun fromJsonToRandomUserObject(response: Response<RandomUserEntity>): RandomUser {
        return RandomUser(
            response.body()?.randomUserResultsEntity?.get(0)?.name?.title,
            response.body()?.randomUserResultsEntity?.get(0)?.name?.firstName,
            response.body()?.randomUserResultsEntity?.get(0)?.name?.lastName,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.large,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.medium,
            response.body()?.randomUserResultsEntity?.get(0)?.picture?.thumbnail,
            response.body()?.randomUserResultsEntity?.get(0)?.gender,
            response.body()?.randomUserResultsEntity?.get(0)?.email,
            response.body()?.randomUserResultsEntity?.get(0)?.phone,
            response.body()?.randomUserResultsEntity?.get(0)?.nationality
        )
    }

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
            userEntity.randomUserResultsEntity?.get(0)?.nationality
        )
    }

}
