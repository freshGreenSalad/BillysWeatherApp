package com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherApi(
    val accessLevel: Int,
    val baseModels: BaseModels,
    val cycleLock: String,
    val explain: Boolean,
    val joinModels: Boolean,
    val points: List<LocationPoint>,
    val time: TimeWeatherAPI,
    val variables: List<String>
)
@Serializable
data class BaseModels(
    val atmospheric: String,
    val wave: String
)
@Serializable
data class LocationPoint(
    val lat: Double,
    val lon: Double
)
@Serializable
data class TimeWeatherAPI(
    val from: String,
    val interval: String,
    val repeat: Int
)
