package com.maggiver.marketshop.favorites.domain

import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.favorites.data.repository.ProductFavoriteRepositoryLocal
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductFavoriteUseCaseImpl @Inject constructor(private val repositoryLocal: ProductFavoriteRepositoryLocal):
ProductFavoriteUseCase {

    override fun isFavoriteProductUseCase(productFavoriteId: Int): Flow<Boolean> =
        repositoryLocal.isFavoriteProductRepository(productFavoriteId)


    override suspend fun getProductFavoriteUseCase(productFavoriteId: Int): ProductEntity? =
        repositoryLocal.getProductFavoriteRepository(productFavoriteId = productFavoriteId)

    override suspend fun insertProductFavoriteUseCase(product: ProductDetailResponse) {
        repositoryLocal.insertProductFavoriteRepository(product)
    }

    override suspend fun removeProductFavoriteUseCase(productDetailResponse: ProductDetailResponse) =
        repositoryLocal.removeProductFavoriteRepository(productDetailResponse = productDetailResponse)

    override fun getAllProductsFavoriteUseCase(): Flow<ResourceState<List<ProductDetailResponse>>> =
        repositoryLocal.getAllProductsFavoriteRepository()


}