package com.maggiver.marketshop.favorites.data.repository

import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ProductFavoriteRepositoryLocal {

    fun isFavoriteProductRepository(productFavoriteId: Int): Flow<Boolean>

    suspend fun getProductFavoriteRepository(productFavoriteId: Int): ProductEntity?

    suspend fun insertProductFavoriteRepository(productDetailResponse: ProductDetailResponse)

    suspend fun removeProductFavoriteRepository(productDetailResponse: ProductDetailResponse)

}