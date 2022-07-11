package meh.daniel.com.tenkeyoho.data

import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApi {
    companion object{
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BASIC
        }
        fun client() = OkHttpClient.Builder().apply {
            addInterceptor(logging)
        }.build()
        fun createApi() : WeatherApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client())
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