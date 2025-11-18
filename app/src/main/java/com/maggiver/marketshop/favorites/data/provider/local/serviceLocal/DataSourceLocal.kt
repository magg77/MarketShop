package com.maggiver.marketshop.favorites.data.provider.local.serviceLocal

import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import kotlinx.coroutines.flow.Flow

interface DataSourceLocal {

    fun searchFavoriteProductId(productFavoriteId: Int): Flow<Boolean>

    suspend fun getProductFavorite(productFavoriteId: Int): ProductEntity?

    suspend fun insertProductRoom(productEntity: ProductEntity)

    suspend fun removeFavorite(productEntity: ProductEntity)

    fun getAllProductsFavorite(): Flow<List<ProductEntity>>

}