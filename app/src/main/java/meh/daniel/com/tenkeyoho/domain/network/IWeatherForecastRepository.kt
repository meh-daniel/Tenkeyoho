package meh.daniel.com.tenkeyoho.domain.network

import meh.daniel.com.tenkeyoho.domain.models.WeatherForecast

interface IWeatherForecastRepository {

    suspend fun loadWeatherForecast(): Result<WeatherForecast>

}