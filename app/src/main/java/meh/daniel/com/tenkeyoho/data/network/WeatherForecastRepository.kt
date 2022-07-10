package meh.daniel.com.tenkeyoho.data.network

import meh.daniel.com.tenkeyoho.domain.models.WeatherForecast
import meh.daniel.com.tenkeyoho.domain.network.IWeatherForecastRepository

class WeatherForecastRepository(
    private val api: IDataSource
) : IWeatherForecastRepository {

    override suspend fun loadWeatherForecast(): Result<WeatherForecast> {
        return api.getWeatherForecast()
    }

}