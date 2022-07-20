package meh.daniel.com.tenkeyoho.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import meh.daniel.com.tenkeyoho.data.db.modelSW.CitySW
import meh.daniel.com.tenkeyoho.data.db.modelSW.WeatherSW

@Database(
    version = 1,
    entities = [
        WeatherSW::class,
        CitySW::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun createAppDataBase(
            context: Context
        ): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "my_weather_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
