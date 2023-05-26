package com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain

import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime

interface WeatherRepository {
    suspend fun getListOfRadiationByTimePoints():List<RadiationByTime>

    suspend fun getListOfPrecipitationByTimePoints():List<RadiationByTime>

    suspend fun getListOfAirTempByTimePoints():List<RadiationByTime>
}