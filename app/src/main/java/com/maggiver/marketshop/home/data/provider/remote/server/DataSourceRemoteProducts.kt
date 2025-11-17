package com.maggiver.marketshop.home.data.provider.remote.server

import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse

interface DataSourceRemoteProducts {

    suspend fun getProductsRemote(): ResourceState<ProductsResponse>

    suspend fun getProductDetailRemote(productId: Int): ResourceState<ProductDetailResponse>

}