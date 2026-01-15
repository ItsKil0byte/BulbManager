package com.example.bulbproject.di

import com.example.bulbproject.data.BulbService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val URL = "http://195.133.53.179:1337/"

    /*
    Умные дяди со стэка сказали, что лучше такие штуки делать синглтон, чтобы не
    плодить их во время запросов. Надеюсь меня не обманули.
    */

    @Provides
    @Singleton
    fun provideBulbService(): BulbService =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
}