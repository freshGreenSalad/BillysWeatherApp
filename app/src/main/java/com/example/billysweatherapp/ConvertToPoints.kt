package com.example.billysweatherapp

import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime

class ConvertToPoints {
    fun convertDoubleListToPointsForGraph(list: List<Double>): List<RadiationByTime> =
        list.mapIndexed { index, radiation ->
            RadiationByTime(time = index, APIValue = radiation.toInt())
        }
}