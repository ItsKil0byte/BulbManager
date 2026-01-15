package com.example.bulbproject.di

import com.example.bulbproject.data.BulbRepository
import com.example.bulbproject.data.BulbRepositoryImpl
import com.example.bulbproject.domain.GetInfoUseCase
import com.example.bulbproject.domain.GetInfoUseCaseImpl
import com.example.bulbproject.domain.SetBrightnessUseCase
import com.example.bulbproject.domain.SetBrightnessUseCaseImpl
import com.example.bulbproject.domain.SetColorUseCase
import com.example.bulbproject.domain.SetColorUseCaseImpl
import com.example.bulbproject.domain.ToggleBulbUseCase
import com.example.bulbproject.domain.ToggleBulbUseCaseImpl
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