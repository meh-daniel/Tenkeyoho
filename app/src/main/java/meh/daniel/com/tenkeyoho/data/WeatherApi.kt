package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApi {

    companion object{
        fun createApi() : WeatherApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }

    @GET("forecast")
    suspend fun getWeathers(
        @Query("appid") appid : String,
        @Query("q") city : String,
        @Query("units") units : String,
        @Query("lang") lang : String
    ) : WeathersNW

}