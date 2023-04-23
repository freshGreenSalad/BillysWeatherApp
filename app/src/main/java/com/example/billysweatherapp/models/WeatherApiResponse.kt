package com.example.billysweatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class weatherApiResponse(
    val dimensions: Dimensions,
    val explanation: Explanation,
    val noDataReasons: NoDataReasons,
    val variables: VariablesX
)
@Serializable
data class Dimensions(
    val point: Point,
    val time: Time
)
@Serializable
data class Explanation(
    val maxAccessLevel: Int,
    val models: Models,
    val variables: Variables
)
@Serializable
data class NoDataReasons(
    val ERROR_INTERNAL: Int,
    val FILL: Int,
    val GAP: Int,
    val GOOD: Int,
    val INVALID_HIGH: Int,
    val INVALID_LOW: Int
)
@Serializable
data class VariablesX(
    @SerialName("radiation.flux.downward.shortwave") val shortwave: RadiationFluxDownwardShortwaveX
)
@Serializable
data class Point(
    val `data`: List<Data>,
    val type: String,
    val units: String
)
@Serializable
data class Time(
    val `data`: List<String>,
    val type: String,
    val units: String
)
@Serializable
data class Data(
    val lat: Double,
    val lon: Double
)
@Serializable
data class Models(
    @SerialName("ecmwf.global")val global: EcmwfGlobal
)
@Serializable
data class Variables(
    @SerialName("radiation.flux.downward.shortwave") val shortwave: RadiationFluxDownwardShortwave
)

@Serializable
data class EcmwfGlobal(
    val modelRuns: List<String>
)
@Serializable
data class RadiationFluxDownwardShortwave(
    val models: ModelsX,
    val used: List<Int>
)
@Serializable
data class ModelsX(
    @SerialName("ecmwf.global")val global: Int
)
@Serializable
data class RadiationFluxDownwardShortwaveX(
    val `data`: List<Double>,
    val dimensions: List<String>,
    val noData: List<Int>,
    val siUnits: String,
    val standardName: String,
    val units: String
)