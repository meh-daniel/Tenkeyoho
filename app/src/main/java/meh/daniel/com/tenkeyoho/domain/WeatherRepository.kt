package meh.daniel.com.tenkeyoho.domain

import meh.daniel.com.tenkeyoho.domain.model.WeatherOfCity

interface WeatherRepository {
    suspend fun getWeather() : WeatherOfCity
    suspend fun getWeather(lat: Double, lon: Double) : WeatherOfCity
}