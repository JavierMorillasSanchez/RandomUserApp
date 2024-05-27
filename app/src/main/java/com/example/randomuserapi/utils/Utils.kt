package com.example.randomuserapi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.data.entities.RandomUserEntity

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

object NetworkState{
    fun isOnline(context: Context, logTag: String): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i(logTag, "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i(logTag, "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i(logTag, "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}

object FilterFunctions{

    fun userIsMale(user:RandomUser): Boolean {
        return user.gender.equals("male")
    }

}
