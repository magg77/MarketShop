package com.maggiver.marketshop.favorites.data.repository

import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.toEntity
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.toProductDetail
import com.maggiver.marketshop.favorites.data.provider.local.serviceLocal.DataSourceLocalImpl
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
            thumbnail = productDetailResponse.thumbnail,
            price = productDetailResponse.price,
            images = productDetailResponse.images
        )

        //localDataSource.insertProductRoom(productEntity = productEntity)
        localDataSource.insertProductRoom(productDetailResponse.toEntity())

    }

    override suspend fun removeProductFavoriteRepository(productDetailResponse: ProductDetailResponse) {
        localDataSource.removeFavorite(productEntity = productDetailResponse.toEntity())
    }

    override suspend fun getAllProductsFavoriteRepository(): Flow<ResourceState<List<ProductDetailResponse>>> =
        flow {

            emit(ResourceState.LoadingState())

            try {

                val productFavorites = withContext(Dispatchers.IO){
                    localDataSource.getAllProductsFavorite()
                        .map { it.toProductDetail() }
                }

                emit(ResourceState.SuccessState(productFavorites))

            } catch (e: Exception){
                emit(ResourceState.FailureState(message = "${e.localizedMessage}"))
            }
        }


}