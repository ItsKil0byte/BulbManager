package com.example.bulbproject.domain

import com.example.bulbproject.data.BulbRepository
import javax.inject.Inject

data class BulbInfo(
    val state: Boolean,
    val brightness: Int,
    val color: String
)

interface GetInfoUseCase {
    suspend fun invoke(): BulbInfo
}

class GetInfoUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : GetInfoUseCase {
    override suspend fun invoke(): BulbInfo {
        return BulbInfo(
            state = repository.getState(),
            brightness = repository.getBrightness(),
            color = repository.getColor().color,
        )
    }
}