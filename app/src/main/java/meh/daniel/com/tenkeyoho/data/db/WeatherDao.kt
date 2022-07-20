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
    suspend fun insertWeathersList(weathers: List<WeatherSW>)

    @Query("DELETE FROM weathers")
    suspend fun deleteAllWeathers()

    @Query("DELETE FROM city")
    suspend fun deleteAllCity()

    @Insert
    suspend fun insertCity(citySW: CitySW)

    @Query("SELECT * FROM city LIMIT 1")
    suspend fun getCity() : CitySW

    @Query("SELECT * FROM weathers")
    suspend  fun getAllWeathers() : List<WeatherSW>

    @Transaction
    suspend fun updateAllWeathersWithCity(weathers: List<WeatherSW>, citySW: CitySW) {
        deleteAllWeathers()
        deleteAllCity()
        insertCity(citySW)
        insertWeathersList(weathers)
    }

}