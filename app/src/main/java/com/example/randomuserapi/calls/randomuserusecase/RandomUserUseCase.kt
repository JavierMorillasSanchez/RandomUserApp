package com.example.randomuserapi.calls.randomuserusecase

import android.util.Log
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RandomUserUseCase {

    val LOG_TAG = "RandomUserUseCase"
    fun fetchCurrencyData(): Thread {

        return Thread{

            val url = URL("https://randomuser.me/api/")
            val connection = url.openConnection() as HttpsURLConnection

            if(connection.responseCode == 200){
                var inputStream = connection.inputStream
                var inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                var request = Gson().fromJson(inputStreamReader, RandomUserEntity::class.java)

                inputStreamReader.close()
                inputStream.close()

                Log.d(LOG_TAG,"Obtenidos los datos de " +
                        "${request.randomUserResultsEntity?.get(0)?.name?.title} " +
                        "${request.randomUserResultsEntity?.get(0)?.name?.firstName} " +
                        "${request.randomUserResultsEntity?.get(0)?.name?.lastName}.")

            } else {
                Log.d(LOG_TAG, "Connection Failed.")
            }

        }

    }

}