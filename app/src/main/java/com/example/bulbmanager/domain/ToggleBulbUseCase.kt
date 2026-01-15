package com.example.bulbmanager.domain

import com.example.bulbmanager.data.BulbRepository
import javax.inject.Inject

interface ToggleBulbUseCase {
    suspend operator fun invoke(): Boolean
}

class ToggleBulbUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
): ToggleBulbUseCase {
    override suspend fun invoke(): Boolean {
        return repository.toggleState()
    }
}