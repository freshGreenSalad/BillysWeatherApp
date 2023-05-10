package com.example.billysweatherapp.di

import com.example.billysweatherapp.features.shortWaveRaditaionFeature.data.RadiationRepository
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface repositoryModule {
    @Binds
    fun privideRadiationRepository(radiationRepository: RadiationRepository): WeatherRepository
}