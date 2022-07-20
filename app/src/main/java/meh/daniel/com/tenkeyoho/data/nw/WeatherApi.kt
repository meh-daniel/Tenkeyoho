package meh.daniel.com.tenkeyoho.data.nw

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApi {
    companion object{
        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BASIC
        }
        private fun createLoggingOkHttpClient() = OkHttpClient.Builder().apply {
            addInterceptor(logging)
        }.build()
        fun createApiOpenWeatherMap() : WeatherApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createLoggingOkHttpClient())
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
    ) : WeatherNW
}