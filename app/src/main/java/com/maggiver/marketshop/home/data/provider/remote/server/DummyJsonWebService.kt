package com.maggiver.marketshop.home.data.provider.remote.server

import com.maggiver.marketshop.core.valueObjects.NoAuth
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyJsonWebService {

    @GET("products")
    @NoAuth
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): Response<ProductDetailResponse>



}