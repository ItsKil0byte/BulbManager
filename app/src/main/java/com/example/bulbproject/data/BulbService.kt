package com.example.bulbproject.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface BulbService {

    @POST("/state/on")
    suspend fun powerOn(): Response<Boolean>

    @POST("/state/off")
    suspend fun powerOff(): Response<Boolean>

    @GET("/state/")
    suspend fun checkPower(): Response<Boolean>
}