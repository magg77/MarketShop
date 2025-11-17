package com.maggiver.marketshop.core.valueObjects

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.favorites.data.provider.local.serviceLocal.LocalServiceProductDAO

@Database(entities = [ProductEntity::class], version = 1, exportSchema = true)
abstract class AppDatabaseRoom : RoomDatabase() {

    abstract fun productDAO(): LocalServiceProductDAO

}