package meh.daniel.com.tenkeyoho

import android.app.Application
import meh.daniel.com.tenkeyoho.data.WeatherApi
import meh.daniel.com.tenkeyoho.data.network.WeatherNetworkRepositoryImpl
import meh.daniel.com.tenkeyoho.domain.network.WeatherNetworkRepository

class App : Application(){
    companion object{
        lateinit var weatherNetworkRepository: WeatherNetworkRepository
            private set
    }
    override fun onCreate() {
        super.onCreate()
        initRepository()
    }
    private fun initRepository() {
        weatherNetworkRepository = WeatherNetworkRepositoryImpl(weatherApi = WeatherApi.createApi())
    }
}