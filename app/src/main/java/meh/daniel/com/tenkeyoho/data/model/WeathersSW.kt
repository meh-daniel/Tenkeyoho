package meh.daniel.com.tenkeyoho.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "weathers"
)
data class WeathersSW(
    @PrimaryKey(autoGenerate = true)
    val idWeathersSW: Long,
    @ColumnInfo( name = "city")
    val city: City,
    @ColumnInfo(name = "cnt")
    val cnt: Int,
    @ColumnInfo(name = "cod")
    val cod: String,
    @ColumnInfo(name = "list")
    val list: List<WeatherInfo>,
    @ColumnInfo(name = "messagename = ")
    val message: Int
) {
    @Entity(
        tableName = "city"
    )
    data class City(
        @PrimaryKey(autoGenerate = true)
        val idCity: Long,
        @ColumnInfo(name = "coord")
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
        @Entity(
            tableName = "coord"
        )
        data class Coord(
            @PrimaryKey(autoGenerate = true)
            val idCoord: Long,
            @ColumnInfo(name = "lat")
            val lat: Double,
            @ColumnInfo(name = "lon")
            val lon: Double
        )
    }
    @Entity(
        tableName = "weathers_info"
    )
    data class WeatherInfo(
        @PrimaryKey(autoGenerate = true)
        val idWeatherInfo: Long,
        @ColumnInfo(name = "clouds")
        val clouds: Clouds,
        @ColumnInfo(name = "dt")
        val dt: Int,
        @ColumnInfo(name = "dt_txt")
        val dtTxt: String,
        @ColumnInfo(name = "main")
        val main: Main,
        @ColumnInfo(name = "pop")
        val pop: Double,
        @ColumnInfo(name = "sys")
        val sys: Sys,
        @ColumnInfo(name = "visibility")
        val visibility: Int,
        @ColumnInfo(name = "weather")
        val weather: List<Weather>,
        @ColumnInfo(name = "wind")
        val wind: Wind
    ) {
        @Entity(
            tableName = "clouds"
        )
        data class Clouds(
            @PrimaryKey(autoGenerate = true)
            val idClouds: Long,
            @ColumnInfo(name = "all")
            val all: Int
        )
        @Entity(
            tableName = "main"
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
        @Entity(
            tableName = "sys"
        )
        data class Sys(
            @PrimaryKey(autoGenerate = true)
            val idSys: Long,
            @ColumnInfo(name = "pod")
            val pod: String
        )
        @Entity(
            tableName = "weather"
        )
        data class Weather(
            @PrimaryKey(autoGenerate = true)
            val idWeather: Long,
            @ColumnInfo(name = "description")
            val description: String,
            @ColumnInfo(name = "icon")
            val icon: String,
            @ColumnInfo(name = "id")
            val id: Int,
            @ColumnInfo(name = "main")
            val main: String
        )
        @Entity(
            tableName = "wind"
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


}