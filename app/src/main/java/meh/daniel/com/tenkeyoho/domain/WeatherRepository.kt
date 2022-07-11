package meh.daniel.com.tenkeyoho.domain

import meh.daniel.com.tenkeyoho.data.model.WeathersNW

interface WeatherRepository {
    suspend fun getWeather() : WeathersNW
}