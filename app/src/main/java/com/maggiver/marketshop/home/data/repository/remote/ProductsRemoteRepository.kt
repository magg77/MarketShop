package com.maggiver.marketshop.home.data.repository.remote

import android.content.Context
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRemoteRepository {

    suspend fun getProductsRemoteRepository(): Flow<ResourceState<ProductsResponse>>

}