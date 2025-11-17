package com.maggiver.marketshop.favorites.data.provider.local.entitiy

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ImagesConverter @Inject constructor() {

    private val gson = Gson()

    @TypeConverter
    fun fromList(images: List<String?>?): String? {
        return gson.toJson(images)
    }

    @TypeConverter
    fun toList(json: String?): List<String?>? {

        if (json.isNullOrEmpty()) return emptyList()

        val type = object : TypeToken<List<String?>>() {}.type
        return gson.fromJson(json, type)

    }



}