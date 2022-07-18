package meh.daniel.com.tenkeyoho.domain.network

import meh.daniel.com.tenkeyoho.data.model.WeathersNW

interface WeatherNetworkRepository {
    suspend fun getWeather() : WeathersNW
}