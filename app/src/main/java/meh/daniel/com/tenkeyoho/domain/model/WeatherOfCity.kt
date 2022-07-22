package meh.daniel.com.tenkeyoho.domain.model

data class WeatherOfCity(
    val nameCity: String,
    val coordinatesOfCity: CoordinatesOfCity,
    val weathers: List<Weather>
)
