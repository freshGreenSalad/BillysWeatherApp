package com.example.billysweatherapp

import android.util.Log
import com.example.billysweatherapp.models.*
import com.example.billysweatherapp.repository.apikey
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RadiationRepository @Inject constructor(
    private val httpClient: HttpClient
):WeatherRepository {


    override suspend fun getRadiation():List<RadiationByTime>{
        val response = httpClient.post("https://forecast-v2.metoceanapi.com/point/time") {
            headers{
                append("x-api-key", apikey)
            }
            contentType(ContentType.Application.Json)
            setBody(getWeatherAppDataClass())
        }

        Log.d("",response.body<String>().toString())
        val rss = Json.decodeFromString<weatherApiResponse>(response.body()).variables.shortwave.data
        Log.d("",rss.toString())
        return convertdoubleListTOPointsForGraph(rss)
    }

    private fun convertdoubleListTOPointsForGraph(list: List<Double>): List<RadiationByTime> {
        val newlist = mutableListOf<RadiationByTime>()

        list.forEachIndexed { index, radiation ->
            newlist.add(RadiationByTime(time = index, radiation = radiation.toInt()))
        }
        return newlist
    }
}

private fun getWeatherAppDataClass():weatherApi{
    val time =  ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(System.currentTimeMillis()),
        ZoneId.of("GMT+12")
    )
    val formatedDate = DateTimeFormatter.ISO_INSTANT.format(time)

    return  weatherApi(
        accessLevel = 10,
        baseModels = BaseModels(
            atmospheric = "auto",
            wave = "auto"
        ),
        cycleLock = "group",
        explain = true,
        joinModels = true,
        points = listOf<LocationPoint>( LocationPoint(
            lon = 174.8393440246582,
            lat = -36.87474588690167
        )
        ),
        time = TimeWeatherAPI(
            from = "2023-04-24T12:00:00Z",// TODO("add the current date here starting at gmt +12")
            interval = "1h",
            repeat = 23,
        ),
        variables = listOf<String>(
            "radiation.flux.downward.shortwave"
        )
    )
}

data class RadiationByTime(
    val time: Int,
    val radiation:Int
)