package com.maggiver.marketshop.home.domain

import android.content.Context
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.home.data.repository.remote.ProductsRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRemoteUseCaseImpl @Inject constructor(private val repository: ProductsRemoteRepository):
    ProductsRemoteUseCase {

    override suspend fun getProductsRemoteUseCase(): Flow<ResourceState<ProductsResponse>> =
        repository.getProductsRemoteRepository()


}