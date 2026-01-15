package com.example.bulbmanager.di

import com.example.bulbmanager.data.BulbRepository
import com.example.bulbmanager.data.BulbRepositoryImpl
import com.example.bulbmanager.domain.GetInfoUseCase
import com.example.bulbmanager.domain.GetInfoUseCaseImpl
import com.example.bulbmanager.domain.SetBrightnessUseCase
import com.example.bulbmanager.domain.SetBrightnessUseCaseImpl
import com.example.bulbmanager.domain.SetColorUseCase
import com.example.bulbmanager.domain.SetColorUseCaseImpl
import com.example.bulbmanager.domain.ToggleBulbUseCase
import com.example.bulbmanager.domain.ToggleBulbUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindBulbRepository(impl: BulbRepositoryImpl): BulbRepository

    @Binds
    fun bindGetInfoUseCase(impl: GetInfoUseCaseImpl): GetInfoUseCase

    @Binds
    fun bindToggleBulbUseCase(impl: ToggleBulbUseCaseImpl): ToggleBulbUseCase

    @Binds
    fun bindSetColorUseCase(impl: SetColorUseCaseImpl): SetColorUseCase

    @Binds
    fun bindSetBrightnessUseCase(impl: SetBrightnessUseCaseImpl): SetBrightnessUseCase

}