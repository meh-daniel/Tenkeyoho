package meh.daniel.com.tenkeyoho.domain.network

import meh.daniel.com.tenkeyoho.data.model.WeathersNW

interface WeatherRepository {
    suspend fun getWeather() : WeathersNW
}