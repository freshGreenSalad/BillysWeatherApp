package com.example.billysweatherapp.features.shortWaveRaditaionFeature.data

import android.util.Log
import com.example.billysweatherapp.ConvertToPoints
import com.example.billysweatherapp.common.data.BasicHttpRequests
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.WeatherRepository
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.*
import io.ktor.client.call.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RadiationRepository @Inject constructor(
    private val basicHttpRequests: BasicHttpRequests
): WeatherRepository {
    override suspend fun getListOfRadiationByTimePoints():List<RadiationByTime>{
        val response = basicHttpRequests.getRadiation("radiation.flux.downward.shortwave")
        Log.d("", response.body())
        val weatherAPIShortwavePoints = Json.decodeFromString<weatherApiResponse>(response.body()).variables.shortwave.data
        return ConvertToPoints().convertDoubleListToPointsForGraph(weatherAPIShortwavePoints)
    }

    override suspend fun getListOfPrecipitationByTimePoints(): List<RadiationByTime> {
        val response = basicHttpRequests.getRadiation("precipitation.rate")
        Log.d("", response.body())
        val weatherAPIShortwavePoints = Json.decodeFromString<rain>(response.body()).variables.shortwave.data
        return ConvertToPoints().convertDoubleListToPointsForGraph(weatherAPIShortwavePoints)
    }

    override suspend fun getListOfAirTempByTimePoints(): List<RadiationByTime> {
        val response = basicHttpRequests.getRadiation("air.temperature.at-2m")
        Log.d("", response.body())
        val weatherAPIShortwavePoints = Json.decodeFromString<temp>(response.body()).variables.shortwave.data
        return ConvertToPoints().convertDoubleListToPointsForGraph(weatherAPIShortwavePoints)
    }
}

