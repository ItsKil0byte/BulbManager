package com.example.bulbproject.domain

import com.example.bulbproject.data.BulbRepository
import javax.inject.Inject

interface GetBulbUseCases {
    suspend operator fun invoke(): Boolean
}

class GetGetBulbUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
): GetBulbUseCases {
    override suspend fun invoke(): Boolean {
        return repository.switchState()
    }
}