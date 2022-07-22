package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.db.AppDataBase
import meh.daniel.com.tenkeyoho.data.db.modelSW.CitySW
import meh.daniel.com.tenkeyoho.data.nw.WeatherApi
import meh.daniel.com.tenkeyoho.data.nw.WeatherNW
import meh.daniel.com.tenkeyoho.domain.WeatherRepository
import meh.daniel.com.tenkeyoho.domain.model.CoordinatesOfCity
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
    override suspend fun getWeather(): WeatherOfCity {
        return try {
            val weathers = weatherApi.getWeatherNWByCity(
                appid = getParamAppId(),
                city = getParamCity(),
                units = getParamUnits(),
                lang = getParamLanguage()
            ).toDomain()
            appDataBase.weatherDao().updateAllWeathersSWWithCity(
                weathers.weathers.toSW(),
                CitySW(
                    name = weathers.nameCity,
                    lat = weathers.coordinatesOfCity.lat,
                    lon = weathers.coordinatesOfCity.lon
                )
            )
            weathers
        }
        catch (e: Exception) {
            val weathers : List<Weather> = appDataBase.weatherDao().getAllWeathersSW().toDomain()
            val city : String = appDataBase.weatherDao().getCityForCurrentWeathersSW().name
            val lat : Double = appDataBase.weatherDao().getCityForCurrentWeathersSW().lat
            val lon : Double = appDataBase.weatherDao().getCityForCurrentWeathersSW().lon
            WeatherOfCity(nameCity = city, weathers = weathers, coordinatesOfCity = CoordinatesOfCity(lat = lat, lon = lon))
        }
    }

    override suspend fun getWeather(lat: Double, lon: Double): WeatherOfCity {
        return try {
            val weathers = weatherApi.getWeatherNWByCoordinates(
                appid = getParamAppId(),
                lat = lat,
                lon = lon,
                units = getParamUnits(),
                lang = getParamLanguage()
            ).toDomain()
            appDataBase.weatherDao().updateAllWeathersSWWithCity(
                weathers.weathers.toSW(),
                CitySW(
                    name = weathers.nameCity,
                    lat = weathers.coordinatesOfCity.lat,
                    lon = weathers.coordinatesOfCity.lon
                )
            )
            weathers
        } catch (e: Exception){
            val weathers : List<Weather> = appDataBase.weatherDao().getAllWeathersSW().toDomain()
            val city : String = appDataBase.weatherDao().getCityForCurrentWeathersSW().name
            val lat : Double = appDataBase.weatherDao().getCityForCurrentWeathersSW().lat
            val lon : Double = appDataBase.weatherDao().getCityForCurrentWeathersSW().lon
            WeatherOfCity(nameCity = city, weathers = weathers, coordinatesOfCity = CoordinatesOfCity(lat = lat, lon = lon))
        }
    }

    private fun getParamAppId() : String{
        return APP_ID
    }

    private fun getParamCity() : String{
        return CITY
    }

    private fun getParamUnits() : String{
        return UNITS
    }

    private fun getParamLanguage() : String{
        return LANG
    }
}