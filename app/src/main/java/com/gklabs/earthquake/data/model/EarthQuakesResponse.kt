package com.gklabs.earthquake.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class EarthQuakesResponse(
    val earthquakes: List<Earthquake>
) {
    @Entity(tableName = "earthquakes")
    data class Earthquake(
        @PrimaryKey val eqid: String, // c0001xgp
        val datetime: String, // 2011-03-11 04:46:23
        val depth: Double, // 24.4
        val lat: Double, // 38.322
        val lng: Double, // 142.369
        val magnitude: Double, // 8.8
        val src: String // us
    )
}