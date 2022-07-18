package meh.daniel.com.tenkeyoho.data

import androidx.room.Database
import androidx.room.RoomDatabase
import meh.daniel.com.tenkeyoho.data.model.*

@Database(
    version = 1,
    entities = [
        WeathersNW::class,
        WeatherInfo::class,
        WeathersSWWithWeatherInfo::class,
        Weather::class,
        WeatherInfoWithWeather::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
}