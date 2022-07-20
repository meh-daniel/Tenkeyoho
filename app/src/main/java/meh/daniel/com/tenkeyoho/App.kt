package meh.daniel.com.tenkeyoho

import android.app.Application
import meh.daniel.com.tenkeyoho.data.WeatherRepositoryImpl
import meh.daniel.com.tenkeyoho.data.db.AppDataBase
import meh.daniel.com.tenkeyoho.data.nw.WeatherApi
import meh.daniel.com.tenkeyoho.domain.WeatherRepository

class App : Application(){

    companion object{
        lateinit var weatherRepository: WeatherRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initRepository()
    }

    private fun initRepository() {
        weatherRepository = WeatherRepositoryImpl(weatherApi = WeatherApi.createApiOpenWeatherMap(),
            appDataBase = AppDataBase.createAppDataBase(this))
    }

}