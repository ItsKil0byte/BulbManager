package com.example.bulbmanager

import com.example.bulbmanager.di.AppComponent
import android.app.Application
import android.content.Context
import com.example.bulbmanager.di.DaggerAppComponent

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