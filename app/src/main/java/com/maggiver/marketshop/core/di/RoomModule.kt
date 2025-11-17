package com.maggiver.marketshop.core.di

import android.content.Context
import androidx.room.Room
import com.maggiver.marketshop.core.valueObjects.AppDatabaseRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    private val DATABASE_NAME = "MarketShop_DB"

    @Singleton
    @Provides
    fun provideInstanceRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabaseRoom::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductsDao(db: AppDatabaseRoom) = db.productDAO()

}