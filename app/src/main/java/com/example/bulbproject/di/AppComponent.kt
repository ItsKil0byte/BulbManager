package com.example.bulbproject.di

import com.example.bulbproject.presentier.MainFragment
import com.example.bulbproject.di.viewModel.ViewModelModule
import dagger.Component
import dagger.Module

@Component(
    modules = [
        AppModule::class
    ]
)
abstract class AppComponent {

    abstract fun inject(fragment: MainFragment)
}

@Module(
    includes = [
        NetworkModule::class,
        AppBindsModule::class,
        ViewModelModule::class
    ]
)
class AppModule