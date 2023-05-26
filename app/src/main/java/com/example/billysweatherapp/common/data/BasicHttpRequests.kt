package com.example.billysweatherapp.common.data

import com.example.billysweatherapp.common.domain.HttpConstants
import com.example.billysweatherapp.common.domain.apiKeys.apikey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject

class BasicHttpRequests @Inject constructor(
    private val client:HttpClient
) {
    suspend fun getRadiation(apiVariable:String):HttpResponse{
        return client.post(HttpConstants.ShortWaveRadiation) {
            headers{
                append("x-api-key", apikey)
            }
            contentType(ContentType.Application.Json)
            setBody(GenerateJsonBodies().getWeatherAppDataClass(apiVariable))
        }
    }
}