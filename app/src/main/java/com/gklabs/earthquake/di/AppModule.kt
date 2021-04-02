package com.gklabs.earthquake.di

import android.app.Application
import androidx.room.Room
import com.gklabs.earthquake.BuildConfig
import com.gklabs.earthquake.data.dao.EarthQuakesDatabase
import com.gklabs.earthquake.data.network.ApiNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideEarthQuakeApi(retrofit: Retrofit): ApiNetwork =
        retrofit.create(ApiNetwork::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app: Application): EarthQuakesDatabase =
        Room.databaseBuilder(app, EarthQuakesDatabase::class.java, "earthquakes_database")
            .build()
}