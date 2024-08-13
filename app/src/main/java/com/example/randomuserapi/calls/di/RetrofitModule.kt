package com.example.randomuserapi.calls.di

import com.example.randomuserapi.calls.data.network.RandomUserApiClient
import com.example.randomuserapi.utils.ApiEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiEndpoint.RANDOM_USER_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomUserApiClient(retrofit: Retrofit): RandomUserApiClient {
        return retrofit.create(RandomUserApiClient::class.java)
    }


}