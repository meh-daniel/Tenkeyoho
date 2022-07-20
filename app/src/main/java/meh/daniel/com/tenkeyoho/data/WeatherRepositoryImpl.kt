package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.db.AppDataBase
import meh.daniel.com.tenkeyoho.data.db.modelSW.CitySW
import meh.daniel.com.tenkeyoho.data.nw.WeatherApi
import meh.daniel.com.tenkeyoho.domain.WeatherRepository
import meh.daniel.com.tenkeyoho.domain.model.Weather
import meh.daniel.com.tenkeyoho.domain.model.WeatherOfCity


private const val APP_ID = "22e2d18f9cd7316c5de47fd73d2ae414"
private const val CITY = "London"
private const val UNITS = "metric"
private const val LANG = "ru"

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val appDataBase: AppDataBase
): WeatherRepository {
    override suspend fun getWeather(): WeatherOfCity{
        return try {
            val weathers = weatherApi.getWeathers(appid = APP_ID, city = CITY, units = UNITS, lang = LANG).toDomain()
            appDataBase.weatherDao().updateAllWeathersWithCity(weathers.weathers.toSW(), CitySW(name = weathers.nameCity))
            weathers
        }
        catch (e: Exception) {
            val weathers : List<Weather> = appDataBase.weatherDao().getAllWeathers().map {
                it.toDomain()
            }
            val city : String = appDataBase.weatherDao().getCity().name
            WeatherOfCity(city, weathers)
        }
    }

}


