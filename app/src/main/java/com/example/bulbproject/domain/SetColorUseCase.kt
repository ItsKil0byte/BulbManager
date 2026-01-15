package com.example.bulbproject.domain

import com.example.bulbproject.data.BulbRepository
import javax.inject.Inject

interface SetColorUseCase {
    suspend operator fun invoke(value: String): Unit
}

class SetColorUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : SetColorUseCase {
    override suspend fun invoke(value: String) {
        repository.setColor(value)
    }
}