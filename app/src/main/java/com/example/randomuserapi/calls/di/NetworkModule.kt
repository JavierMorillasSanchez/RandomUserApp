package com.example.randomuserapi.calls.di

import com.example.randomuserapi.calls.data.network.RandomUserApiClient
import com.example.randomuserapi.utils.ApiUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiUrl.randomUserApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomUserApiClient(retrofit: Retrofit): RandomUserApiClient {
        return retrofit.create(RandomUserApiClient::class.java)
    }


}