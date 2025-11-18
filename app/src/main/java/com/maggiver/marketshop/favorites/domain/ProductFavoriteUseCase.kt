package com.maggiver.marketshop.favorites.domain

import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import kotlinx.coroutines.flow.Flow

interface ProductFavoriteUseCase {

    fun isFavoriteProductUseCase(productFavoriteId: Int): Flow<Boolean>

    suspend fun getProductFavoriteUseCase(productFavoriteId: Int): ProductEntity?

    suspend fun insertProductFavoriteUseCase(product: ProductDetailResponse)

    suspend fun removeProductFavoriteUseCase(productDetailResponse: ProductDetailResponse)

    fun getAllProductsFavoriteUseCase(): Flow<ResourceState<List<ProductDetailResponse>>>

}