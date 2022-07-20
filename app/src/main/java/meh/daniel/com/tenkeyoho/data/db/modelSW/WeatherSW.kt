package meh.daniel.com.tenkeyoho.data.db.modelSW

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "weathers"
)
data class WeatherSW(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "temp")
    val temp: Double,
    @ColumnInfo(name = "dtTxt")
    val dtTxt: String
)

