package com.example.billysweatherapp.common.data

import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.BaseModels
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.LocationPoint
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.TimeWeatherAPI
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.WeatherApi

class GenerateJsonBodies {

    fun getWeatherAppDataClass(apiVariable:String): WeatherApi {
        return  WeatherApi(
            accessLevel = 10,
            baseModels = BaseModels(
                atmospheric = "auto",
                wave = "auto"
            ),
            cycleLock = "group",
            explain = true,
            joinModels = true,
            points = listOf(
                LocationPoint(
                    lon = 174.83505249023438,
                    lat = -36.8687722165491
                )
            ),
            time = TimeWeatherAPI(
                from = TimeForApp().getTimeForApi(),
                interval = "1h",
                repeat = 23,
            ),
            variables = listOf<String>(
                apiVariable
            )
        )
    }
}