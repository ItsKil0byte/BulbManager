package com.example.bulbmanager.domain

import com.example.bulbmanager.data.BulbRepository
import javax.inject.Inject

interface SetBrightnessUseCase {
    suspend operator fun invoke(value: Int): Unit
}

class SetBrightnessUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : SetBrightnessUseCase {
    override suspend fun invoke(value: Int) {
        repository.setBrightness(value)
    }
}