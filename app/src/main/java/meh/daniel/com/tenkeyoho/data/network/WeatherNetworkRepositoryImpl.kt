package meh.daniel.com.tenkeyoho.data.network

import meh.daniel.com.tenkeyoho.data.WeatherApi
import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import meh.daniel.com.tenkeyoho.domain.network.WeatherNetworkRepository


private const val APP_ID = "22e2d18f9cd7316c5de47fd73d2ae414"
private const val CITY = "London"
private const val UNITS = "metric"
private const val LANG = "ru"

class WeatherNetworkRepositoryImpl(private val weatherApi: WeatherApi) : WeatherNetworkRepository {
    override suspend fun getWeather(): WeathersNW {
        return weatherApi.getWeathers(appid = APP_ID, city = CITY, units = UNITS, lang = LANG)
    }
}