package com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class temp(
    val dimensions: Dimensions,
    val explanation: Explanationtemp,
    val noDataReasons: NoDataReasons,
    val variables: VariablesXtemp
)

@Serializable
data class Explanationtemp(
    val maxAccessLevel: Int,
    val models: Models,
    val variables: Variablestemp
)
@Serializable
data class VariablesXtemp(
    @SerialName("air.temperature.at-2m") val shortwave: RadiationFluxDownwardShortwaveX
)

@Serializable
data class Variablestemp(
    @SerialName("air.temperature.at-2m") val shortwave: RadiationFluxDownwardShortwave
)