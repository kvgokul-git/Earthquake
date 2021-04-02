package com.gklabs.earthquake.data.dao

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gklabs.earthquake.data.model.EarthQuakesResponse
import junit.framework.TestCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.internal.Util
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EarthQuakesDatabaseTest : TestCase() {

    private lateinit var database: EarthQuakesDatabase
    private lateinit var dao: EarthquakesDAO

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, EarthQuakesDatabase::class.java).build()
        dao = database.earthQuakesDAO()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertAndFetchEarthQuake() = runBlocking {
        val earthquake = EarthQuakesResponse.Earthquake(
            "c0001xgp",
            "2011-03-11 04:46:23",
            24.4,
            38.322,
            142.369,
            8.8,
            "us"
        )
        val earthquakes = Util.immutableList(earthquake)
        dao.insertEarthQuakes(earthquakes);
        val result = dao.getAllEarthQuakes()
    }

}


