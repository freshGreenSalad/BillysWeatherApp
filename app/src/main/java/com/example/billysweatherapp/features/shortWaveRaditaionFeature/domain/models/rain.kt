package com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class rain(
    val dimensions: Dimensions,
    val explanation: Explanationrain,
    val noDataReasons: NoDataReasons,
    val variables: VariablesXrain
)

@Serializable
data class Explanationrain(
    val maxAccessLevel: Int,
    val models: Models,
    val variables: Variablesrain
)

@Serializable
data class VariablesXrain(
      @SerialName("precipitation.rate") val shortwave: RadiationFluxDownwardShortwaveX
)

@Serializable
data class Variablesrain(
    @SerialName("precipitation.rate") val shortwave: RadiationFluxDownwardShortwave
)