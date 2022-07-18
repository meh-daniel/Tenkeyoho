package meh.daniel.com.tenkeyoho.data.model

import androidx.room.*

@Entity(
    tableName = "weathers"
)
data class WeathersSW(
    @PrimaryKey(autoGenerate = true)
    val idWeathersSW: Long,
    @Embedded
    val city: City,
    @ColumnInfo(name = "cnt")
    val cnt: Int,
    @ColumnInfo(name = "cod")
    val cod: String,
    @ColumnInfo(name = "message")
    val message: Int
) {
    data class City(
        @PrimaryKey(autoGenerate = true)
        val idCity: Long,
        @Embedded
        val coord: Coord,
        @ColumnInfo(name = "country")
        val country: String,
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "population")
        val population: Int,
        @ColumnInfo(name = "sunrise")
        val sunrise: Int,
        @ColumnInfo(name = "sunset")
        val sunset: Int,
        @ColumnInfo(name = "timezone")
        val timezone: Int
    ) {
        data class Coord(
            @PrimaryKey(autoGenerate = true)
            val idCoord: Long,
            @ColumnInfo(name = "lat")
            val lat: Double,
            @ColumnInfo(name = "lon")
            val lon: Double
        )
    }
}

@Entity(
    tableName = "weathers_info"
)
data class WeatherInfo(
    @PrimaryKey(autoGenerate = true)
    val idWeatherInfo: Long,
    @Embedded
    val clouds: Clouds,
    @ColumnInfo(name = "dt")
    val dt: Int,
    @ColumnInfo(name = "dt_txt")
    val dtTxt: String,
    @Embedded
    val main: Main,
    @ColumnInfo(name = "pop")
    val pop: Double,
    @Embedded
    val sys: Sys,
    @ColumnInfo(name = "visibility")
    val visibility: Int,
    @Embedded
    val wind: Wind,
    val weathersSwId : Long
) {
    data class Clouds(
        @PrimaryKey(autoGenerate = true)
        val idClouds: Long,
        @ColumnInfo(name = "all")
        val all: Int
    )
    data class Main(
        @PrimaryKey(autoGenerate = true)
        val idMain: Long,
        @ColumnInfo(name = "feels_like")
        val feelsLike: Double,
        @ColumnInfo(name = "grnd_level")
        val grndLevel: Int,
        @ColumnInfo(name = "humidity")
        val humidity: Int,
        @ColumnInfo(name = "pressure")
        val pressure: Int,
        @ColumnInfo(name = "sea_level")
        val seaLevel: Int,
        @ColumnInfo(name = "temp")
        val temp: Double,
        @ColumnInfo(name = "temp_kf")
        val tempKf: Double,
        @ColumnInfo(name = "temp_max")
        val tempMax: Double,
        @ColumnInfo(name = "temp_min")
        val tempMin: Double
    )
    data class Sys(
        @PrimaryKey(autoGenerate = true)
        val idSys: Long,
        @ColumnInfo(name = "pod")
        val pod: String
    )
    data class Wind(
        @PrimaryKey(autoGenerate = true)
        val idWind: Long,
        @ColumnInfo(name = "deg")
        val deg: Int,
        @ColumnInfo(name = "gust")
        val gust: Double,
        @ColumnInfo(name = "speed")
        val speed: Double
    )
}

@Entity(
    tableName = "weather"
)
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val idWeather: Long,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "main")
    val main: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

data class WeathersSWWithWeatherInfo(
    @Embedded
    val weathersSW: WeathersSW,
    @Relation(
        parentColumn = "idWeathersSW",
        entityColumn = "idWeatherInfo"
    )
    val weathersInfo: List<WeatherInfo>
)

data class WeatherInfoWithWeather(
    @Embedded
    val weatherInfo: WeatherInfo,
    @Relation(
        parentColumn = "idWeatherInfo",
        entityColumn = "idWeather"
    )
    val weather: List<Weather>
)