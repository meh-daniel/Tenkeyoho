package meh.daniel.com.tenkeyoho.data

import androidx.room.Delete
import androidx.room.Insert
import meh.daniel.com.tenkeyoho.data.model.WeathersSW

interface WeathersDao {


    @Insert
    fun insertAll(weathers: WeathersSW)

    @Delete
    fun deleteWeathers(weathers: WeathersSW)



}