package com.maggiver.marketshop.favorites.data.repository

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.toEntity
import com.maggiver.marketshop.favorites.data.provider.local.serviceLocal.DataSourceLocalImpl
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductFavoriteRepositoryLocalImpl @Inject constructor(private val localDataSource: DataSourceLocalImpl):
    ProductFavoriteRepositoryLocal {

    override fun isFavoriteProductRepository(productFavoriteId: Int): Flow<Boolean> {

        return localDataSource.searchFavoriteProductId(productFavoriteId = productFavoriteId)
    }

    override suspend fun getProductFavoriteRepository(productFavoriteId: Int): ProductEntity? {
        return localDataSource.getProductFavorite(productFavoriteId = productFavoriteId)
    }

    override suspend fun insertProductFavoriteRepository(productDetailResponse: ProductDetailResponse) {

        val productEntity = ProductEntity(
            id = productDetailResponse.id,
            title = productDetailResponse.title,
            description = productDetailResponse.description,
            rating = productDetailResponse.rating,
            images = productDetailResponse.images
        )

        //localDataSource.insertProductRoom(productEntity = productEntity)
        localDataSource.insertProductRoom(productDetailResponse.toEntity())

    }

    override suspend fun removeProductFavoriteRepository(productDetailResponse: ProductDetailResponse) {
        localDataSource.removeFavorite(productEntity = productDetailResponse.toEntity())
    }


}