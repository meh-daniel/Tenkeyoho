package meh.daniel.com.tenkeyoho.data.storage

import androidx.room.Insert
import meh.daniel.com.tenkeyoho.data.model.WeathersSW

interface WeathersDao {


    @Insert
    fun insertAll(weathers: WeathersSW)

}