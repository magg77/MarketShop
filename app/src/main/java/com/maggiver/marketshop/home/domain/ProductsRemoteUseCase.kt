package com.maggiver.marketshop.home.domain

import android.content.Context
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRemoteUseCase {

    suspend fun getProductsRemoteUseCase(): Flow<ResourceState<ProductsResponse>>

    suspend fun getProductDetailUseCase(productId: Int): Flow<ResourceState<ProductDetailResponse>>

}