package fuwafuwa.time.core_data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import fuwafuwa.time.core.model.app.AppSize
import fuwafuwa.time.core.model.app.CacheSize
import fuwafuwa.time.core.model.app.DataSize
import org.json.JSONObject

object Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromAppSize(appSize: AppSize): String {
        return gson.toJson(appSize)
    }

    @TypeConverter
    fun toAppSize(string: String): AppSize {
        return gson.fromJson(string, AppSize::class.java)
    }

    @TypeConverter
    fun fromDataSize(dataSize: DataSize): String {
        return gson.toJson(dataSize)
    }

    @TypeConverter
    fun toDataSize(string: String): DataSize {
        return gson.fromJson(string, DataSize::class.java)
    }

    @TypeConverter
    fun fromCacheSize(cacheSize: CacheSize): String {
        return gson.toJson(cacheSize)
    }

    @TypeConverter
    fun toCacheSize(string: String): CacheSize {
        return gson.fromJson(string, CacheSize::class.java)
    }
}