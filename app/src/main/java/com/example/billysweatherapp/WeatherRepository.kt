package com.example.billysweatherapp

interface WeatherRepository {
    suspend fun getRadiation():List<RadiationByTime>
}