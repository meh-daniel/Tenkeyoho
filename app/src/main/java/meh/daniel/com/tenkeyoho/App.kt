package meh.daniel.com.tenkeyoho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import meh.daniel.com.tenkeyoho.data.WeatherApi
import meh.daniel.com.tenkeyoho.data.WeatherRepositoryImpl
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
        weatherRepository = WeatherRepositoryImpl(weatherApi = WeatherApi.createApi())
    }
}