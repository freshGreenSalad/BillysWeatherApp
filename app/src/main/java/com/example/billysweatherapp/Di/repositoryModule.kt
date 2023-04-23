package com.example.billysweatherapp.Di

import com.example.billysweatherapp.RadiationRepository
import com.example.billysweatherapp.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface repositoryModule {

    @Binds
    fun privideRadiationRepository(radiationRepository: RadiationRepository):WeatherRepository
}