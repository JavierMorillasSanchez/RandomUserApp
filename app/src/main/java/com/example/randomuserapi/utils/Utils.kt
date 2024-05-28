package com.example.randomuserapi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.randomuserapi.calls.data.RandomUser
import com.example.randomuserapi.calls.data.model.RandomUserModel

object ApiUrl{
    const val randomUserApiUrl = "https://randomuser.me/api/"
}

object IntentExtrasName{
    const val numberOfUsers = "numberOfUsers"
}

object TransformEntity{

    fun fromEntityToUser(userEntity: RandomUserModel): RandomUser {
        return RandomUser(
            userEntity.randomUserResultsModel?.get(0)?.name?.title,
            userEntity.randomUserResultsModel?.get(0)?.name?.firstName,
            userEntity.randomUserResultsModel?.get(0)?.name?.lastName,
            userEntity.randomUserResultsModel?.get(0)?.picture?.large,
            userEntity.randomUserResultsModel?.get(0)?.picture?.medium,
            userEntity.randomUserResultsModel?.get(0)?.picture?.thumbnail,
            userEntity.randomUserResultsModel?.get(0)?.gender,
            userEntity.randomUserResultsModel?.get(0)?.email,
            userEntity.randomUserResultsModel?.get(0)?.phone,
            userEntity.randomUserResultsModel?.get(0)?.nationality,
            userEntity.randomUserResultsModel?.get(0)?.location?.street?.name,
            userEntity.randomUserResultsModel?.get(0)?.location?.street?.number,
            userEntity.randomUserResultsModel?.get(0)?.dob?.getBirthdateFormatted(),
            userEntity.randomUserResultsModel?.get(0)?.dob?.age,
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

    fun userIsMale(user: RandomUser): Boolean {
        return user.gender.equals("male")
    }

}

object RoomUtils{
    const val RANDOM_USER_TABLE_NAME = "random_user_table"
}
