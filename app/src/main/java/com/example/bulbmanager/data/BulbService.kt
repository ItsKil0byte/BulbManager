package com.example.bulbmanager.data

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

    @GET("/brightness/current")
    suspend fun getBrightness(): Response<Int>

    @POST("/brightness/")
    suspend fun setBrightness(@Query("level") value: Int): Response<Unit>

    @GET("/color/current")
    suspend fun getColor(): Response<ColorDTO>

    @POST("/color/")
    suspend fun setColor(@Query("color") color: String): Response<Unit>
}