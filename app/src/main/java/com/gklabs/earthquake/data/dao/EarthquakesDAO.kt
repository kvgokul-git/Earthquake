package com.gklabs.earthquake.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gklabs.earthquake.data.model.EarthQuakesResponse
import kotlinx.coroutines.flow.Flow


@Dao
interface EarthquakesDAO {

    @Query("SELECT * FROM earthquakes")
    fun getAllEarthQuakes(): Flow<List<EarthQuakesResponse.Earthquake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarthQuakes(earthquakes: List<EarthQuakesResponse.Earthquake>)

    @Query("DELETE FROM earthquakes")
    suspend fun deleteAllEarthQuakes()

}