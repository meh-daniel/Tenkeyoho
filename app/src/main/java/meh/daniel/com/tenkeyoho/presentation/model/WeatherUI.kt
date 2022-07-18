package meh.daniel.com.tenkeyoho.presentation.model

sealed class WeatherUI {
    data class Weathers(
        val nameCity: String,
        val list: List<WeatherInfo>
    ) {
        data class WeatherInfo(
            val dtTxt: String,
            val temp: Double
        )
    }
}