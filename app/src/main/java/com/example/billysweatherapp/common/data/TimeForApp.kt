package com.example.billysweatherapp.common.data

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TimeForApp {
    fun getTimeForApi():String {
        val time = ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(System.currentTimeMillis()),
            ZoneId.of("GMT+12")
        )
        val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T12:00:00Z'")

        return time.format(formater)
    }
}