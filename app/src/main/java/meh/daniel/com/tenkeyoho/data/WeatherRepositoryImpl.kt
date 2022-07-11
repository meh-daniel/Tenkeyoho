package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import meh.daniel.com.tenkeyoho.domain.WeatherRepository


const val APP_ID = "22e2d18f9cd7316c5de47fd73d2ae414"
const val CITY = "London"
const val UNITS = "metric"
const val LANG = "ru"

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(): WeathersNW {
        return weatherApi.getWeathers(appid = APP_ID, city = CITY, units = UNITS, lang = LANG)
    }
}