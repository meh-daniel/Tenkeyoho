package meh.daniel.com.tenkeyoho.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import meh.daniel.com.tenkeyoho.data.db.modelSW.CitySW
import meh.daniel.com.tenkeyoho.data.db.modelSW.WeatherSW

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeathersSWList(weathers: List<WeatherSW>)

    @Query("DELETE FROM weathers")
    suspend fun deleteAllWeathersSW()

    @Query("DELETE FROM city")
    suspend fun deleteAllCitySW()

    @Insert
    suspend fun insertCitySW(citySW: CitySW)

    @Query("SELECT * FROM city LIMIT 1")
    suspend fun getCityForCurrentWeathersSW() : CitySW

    @Query("SELECT * FROM weathers")
    suspend  fun getAllWeathersSW() : List<WeatherSW>

    @Transaction
    suspend fun updateAllWeathersSWWithCity(weathers: List<WeatherSW>, citySW: CitySW) {
        deleteAllWeathersSW()
        deleteAllCitySW()
        insertCitySW(citySW)
        insertWeathersSWList(weathers)
    }

}