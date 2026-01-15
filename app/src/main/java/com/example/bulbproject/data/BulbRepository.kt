package com.example.bulbproject.data

import javax.inject.Inject

interface BulbRepository {
    suspend fun getState(): Boolean
    suspend fun toggleState(): Boolean
    suspend fun setBrightness(value: Int): Unit
    suspend fun setColor(color: String): Unit

}

class BulbRepositoryImpl @Inject constructor(
    private val service: BulbService
) : BulbRepository {
    override suspend fun getState(): Boolean {
        return service.getState().body() ?: false
    }

    override suspend fun toggleState(): Boolean {
        val currentState = getState()

        if (currentState) {
            service.powerOff()
        } else {
            service.powerOn()
        }

        return !currentState
    }

    override suspend fun setBrightness(value: Int) {
        service.setBrightness(value)
    }

    override suspend fun setColor(color: String) {
        service.setColor(color)
    }

}