package com.example.bulbmanager.di

import com.example.bulbmanager.presentier.MainFragment
import com.example.bulbmanager.di.viewModel.ViewModelModule
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
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