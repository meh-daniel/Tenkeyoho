package meh.daniel.com.tenkeyoho.data.network

import meh.daniel.com.tenkeyoho.domain.models.WeatherForecast
import retrofit2.http.GET

interface IDataSource {

    @GET("2.5/forecast?lat=55.004179&lon=82.925866&appid=22e2d18f9cd7316c5de47fd73d2ae414")
    suspend fun getWeatherForecast() : Result<WeatherForecast>

}