package com.example.randomuserapi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.randomuserapi.calls.data.database.entities.RandomUserEntity
import com.example.randomuserapi.calls.domain.model.RandomUser
import com.example.randomuserapi.calls.data.model.RandomUserModel

object ApiEndpoint{
    const val RANDOM_USER_API_ENDPOINT = "https://randomuser.me/api/"
}

object IntentExtrasName{
    const val NUMBER_OF_USERS = "numberOfUsers"
    const val GET_USERS_FROM_DATABASE = "getUsersFromDatabase"
}

object MockedUser{
    val mockedUser: RandomUser? = RandomUser(
        "Don",
        "Juan",
        "Tenorio",
        null,
        null,
        null,
        "male",
        "direcciónemail@mail.com",
        "678 952 566",
        null,
        null,
        null,
        null,
        null
    )
}

object TransformObject{

    fun fromModelToUser(randomUserModel: RandomUserModel): RandomUser {
        return RandomUser(
            randomUserModel.randomUserResultsModel?.get(0)?.name?.title,
            randomUserModel.randomUserResultsModel?.get(0)?.name?.firstName,
            randomUserModel.randomUserResultsModel?.get(0)?.name?.lastName,
            randomUserModel.randomUserResultsModel?.get(0)?.picture?.large,
            randomUserModel.randomUserResultsModel?.get(0)?.picture?.medium,
            randomUserModel.randomUserResultsModel?.get(0)?.picture?.thumbnail,
            randomUserModel.randomUserResultsModel?.get(0)?.gender,
            randomUserModel.randomUserResultsModel?.get(0)?.email,
            randomUserModel.randomUserResultsModel?.get(0)?.phone,
            randomUserModel.randomUserResultsModel?.get(0)?.nationality,
            randomUserModel.randomUserResultsModel?.get(0)?.location?.street?.name,
            randomUserModel.randomUserResultsModel?.get(0)?.location?.street?.number,
            randomUserModel.randomUserResultsModel?.get(0)?.dob?.getBirthdateFormatted(),
            randomUserModel.randomUserResultsModel?.get(0)?.dob?.age,
        )
    }

    fun fromEntityToUser(randomUserEntity: RandomUserEntity): RandomUser {
        return RandomUser(
            randomUserEntity.title,
            randomUserEntity.firstName,
            randomUserEntity.lastName,
            randomUserEntity.pictureLarge,
            randomUserEntity.pictureMedium,
            randomUserEntity.pictureThumbnail,
            randomUserEntity.gender,
            randomUserEntity.email,
            randomUserEntity.phone,
            randomUserEntity.nationality,
            randomUserEntity.address,
            randomUserEntity.addressNumber,
            randomUserEntity.birthday,
            randomUserEntity.age
        )
    }

    fun fromUserToDatabaseEntity(randomUser: RandomUser): RandomUserEntity {
        return RandomUserEntity(
            title = randomUser.title,
            firstName = randomUser.firstName,
            lastName = randomUser.lastName,
            pictureLarge = randomUser.pictureLarge,
            pictureMedium = randomUser.pictureMedium,
            pictureThumbnail = randomUser.pictureThumbnail,
            gender = randomUser.gender,
            email = randomUser.email,
            phone = randomUser.phone,
            nationality = randomUser.nationality,
            address = randomUser.address,
            addressNumber = randomUser.addressNumber,
            birthday = randomUser.birthday,
            age = randomUser.age
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
