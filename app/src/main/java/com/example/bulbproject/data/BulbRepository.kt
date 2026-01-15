package com.example.bulbproject.data

import javax.inject.Inject

interface BulbRepository {
    suspend fun switchState(): Boolean
}

class BulbRepositoryImpl @Inject constructor(
    private val service: BulbService
): BulbRepository {
    override suspend fun switchState(): Boolean {
        val response = service.checkPower()

        if (response.body() == true) {
            service.powerOff()
            return false
        }
        else {
            service.powerOn()
            return true
        }
    }

}