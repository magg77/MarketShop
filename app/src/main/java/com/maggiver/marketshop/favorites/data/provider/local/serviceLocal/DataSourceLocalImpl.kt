package com.maggiver.marketshop.favorites.data.provider.local.serviceLocal

import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataSourceLocalImpl @Inject constructor(private val localService: LocalServiceProductDAO):
    DataSourceLocal {

    override fun searchFavoriteProductId(productFavoriteId: Int): Flow<Boolean> =
        localService.searchFavoriteProductId(productFavoriteId).map { it > 0 }

    override suspend fun getProductFavorite(productFavoriteId: Int): ProductEntity? =
        localService.getProductFavorite(productFavoriteId)

    override suspend fun insertProductRoom(productEntity: ProductEntity) {
        localService.insertProduct(productEntity)
    }

    override suspend fun removeFavorite(productEntity: ProductEntity) {
        localService.getProductFavorite(productEntity.id)?.let { localService.deleteFavorite(productEntity = productEntity) }
    }

    override suspend fun getAllProductsFavorite(): List<ProductEntity> =
        localService.getAllProductsFavorite()


}