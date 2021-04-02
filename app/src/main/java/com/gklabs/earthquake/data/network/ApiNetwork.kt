package com.gklabs.earthquake.data.network

import com.gklabs.earthquake.BuildConfig
import com.gklabs.earthquake.data.model.EarthQuakesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNetwork {

    @GET("earthquakesJSON?")
    suspend fun getEarthQuakes(
        @Query("north") north: Double,
        @Query("south") south: Double,
        @Query("east") east: Double,
        @Query("west") west: Double,
        @Query("formatted") formatted: Boolean = true,
        @Query("username") username: String = BuildConfig.API_USER
    ): EarthQuakesResponse
}