package com.example.bulbproject

import com.example.bulbproject.di.AppComponent
import android.app.Application
import android.content.Context
import com.example.bulbproject.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()
    }

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> this.appComponent
        else -> this.applicationContext.appComponent
    }