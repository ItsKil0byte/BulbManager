package com.example.bulbproject.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BulbService {

    @GET("/state/")
    suspend fun getState(): Response<Boolean>

    @POST("/state/on")
    suspend fun powerOn(): Response<Unit>

    @POST("/state/off")
    suspend fun powerOff(): Response<Unit>

    @POST("/state/brightness")
    suspend fun setBrightness(@Query("value") value: Int): Response<Unit>

    @POST("state/color")
    suspend fun setColor(@Query("color") color: String): Response<Unit>
}