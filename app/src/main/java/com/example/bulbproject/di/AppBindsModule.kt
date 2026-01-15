package com.example.bulbproject.di

import com.example.bulbproject.data.BulbRepository
import com.example.bulbproject.data.BulbRepositoryImpl
import com.example.bulbproject.domain.GetBulbUseCases
import com.example.bulbproject.domain.GetGetBulbUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindBulbRepository(impl: BulbRepositoryImpl): BulbRepository

    @Binds
    fun bindGetBulbUseCases(impl: GetGetBulbUseCaseImpl): GetBulbUseCases

}