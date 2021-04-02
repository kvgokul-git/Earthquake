package com.gklabs.earthquake.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gklabs.earthquake.data.model.EarthQuakesResponse

@Database(entities = [EarthQuakesResponse.Earthquake::class], version = 1)
abstract class EarthQuakesDatabase : RoomDatabase() {

    abstract fun earthQuakesDAO(): EarthquakesDAO
}