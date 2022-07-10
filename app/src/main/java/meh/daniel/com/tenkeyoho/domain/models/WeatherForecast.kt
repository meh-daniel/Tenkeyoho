package meh.daniel.com.tenkeyoho.domain.models


import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("city")
    val city: City?,
    @SerializedName("list")
    val list: List<InfoWeatherDay>
)