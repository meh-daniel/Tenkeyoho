//package meh.daniel.com.tenkeyoho.domain.model
//
//data class WeathersNW(
//    val city: City,
//    val cnt: Int,
//    val cod: String,
//    val list: List<WeatherInfo>,
//    val message: Int
//) {
//    data class City(
//        val coord: Coord,
//        val country: String,
//        val id: Int,
//        val name: String,
//        val population: Int,
//        val sunrise: Int,
//        val sunset: Int,
//        val timezone: Int
//    ) {
//        data class Coord(
//            val lat: Double,
//            val lon: Double
//        )
//    }
//
//    data class WeatherInfo(
//        val clouds: Clouds,
//        val dt: Int,
//        val dtTxt: String,
//        val main: Main,
//        val pop: Double,
//        val sys: Sys,
//        val visibility: Int,
//        val weather: List<Weather>,
//        val wind: Wind
//    ) {
//        data class Clouds(
//            val all: Int
//        )
//
//        data class Main(
//            val feelsLike: Double,
//            val grndLevel: Int,
//            val humidity: Int,
//            val pressure: Int,
//            val seaLevel: Int,
//            val temp: Double,
//            val tempKf: Double,
//            val tempMax: Double,
//            val tempMin: Double
//        )
//
//        data class Sys(
//            val pod: String
//        )
//
//        data class Weather(
//            val description: String,
//            val icon: String,
//            val id: Int,
//            val main: String
//        )
//
//        data class Wind(
//            val deg: Int,
//            val gust: Double,
//            val speed: Double
//        )
//    }
//}