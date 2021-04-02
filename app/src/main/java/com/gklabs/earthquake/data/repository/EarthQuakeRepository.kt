package com.gklabs.earthquake.data.repository

import androidx.room.withTransaction
import com.gklabs.earthquake.data.dao.EarthQuakesDatabase
import com.gklabs.earthquake.data.network.ApiNetwork
import com.gklabs.earthquake.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class EarthQuakeRepository @Inject constructor(
    private val api: ApiNetwork,
    private val database: EarthQuakesDatabase
) {
    private val earthquakesDAO = database.earthQuakesDAO()

    fun getEarthQuakes(
        north: Double,
        south: Double,
        east: Double,
        west: Double
    ) = networkBoundResource(
        query = {
            earthquakesDAO.getAllEarthQuakes()
        },
        fetch = {
            api.getEarthQuakes(north, south, east, west)
        },
        saveFetchResult = {
            database.withTransaction {
                earthquakesDAO.deleteAllEarthQuakes()
                earthquakesDAO.insertEarthQuakes(it.earthquakes)
            }
        }
    )
}