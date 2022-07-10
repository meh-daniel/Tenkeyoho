package meh.daniel.com.tenkeyoho.di

import dagger.Module
import dagger.Provides
import meh.daniel.com.tenkeyoho.data.network.IDataSource
import meh.daniel.com.tenkeyoho.data.network.WeatherForecastRepository
import meh.daniel.com.tenkeyoho.domain.network.IWeatherForecastRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun filmRepository(
        api: IDataSource
    ): IWeatherForecastRepository = WeatherForecastRepository(api)

}