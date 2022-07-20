package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.db.modelSW.WeatherSW
import meh.daniel.com.tenkeyoho.data.nw.WeatherNW
import meh.daniel.com.tenkeyoho.domain.model.Weather
import meh.daniel.com.tenkeyoho.domain.model.WeatherOfCity

internal fun WeatherNW.toDomain(): WeatherOfCity {
    return WeatherOfCity(
        nameCity = city.name,
        weathers = list.map {
            it.toDomain()
        }
    )
}
internal fun WeatherNW.WeatherInfo.toDomain() : Weather {
    return Weather(
        dtTxt = dtTxt,
        temp = main.temp
    )
}

internal fun WeatherSW.toDomain() : Weather {
    return Weather(
        dtTxt = dtTxt,
        temp = temp
    )
}

internal fun List<Weather>.toSW(): List<WeatherSW> =
    map {
        it.toSW()
    }

internal fun Weather.toSW() =
    WeatherSW(
        temp = temp,
        dtTxt = dtTxt
    )

internal fun  List<WeatherSW>.toDomain(): List<Weather> {
    return map {
        it.toDomain()
    }
}
