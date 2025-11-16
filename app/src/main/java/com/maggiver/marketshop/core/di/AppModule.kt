package com.maggiver.marketshop.core.di

import com.maggiver.marketshop.home.data.provider.remote.server.DataSourceRemoteProducts
import com.maggiver.marketshop.home.data.provider.remote.server.DataSourceRemoteProductsImpl
import com.maggiver.marketshop.home.data.repository.remote.ProductsRemoteRepository
import com.maggiver.marketshop.home.data.repository.remote.ProductsRemoteRepositoryImpl
import com.maggiver.marketshop.home.domain.ProductsRemoteUseCase
import com.maggiver.marketshop.home.domain.ProductsRemoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {


    // products

    @Binds
    abstract fun productsRemoteUseCase(productsUseCase: ProductsRemoteUseCaseImpl): ProductsRemoteUseCase

    @Binds
    abstract fun productsRemoteRepository(productsRepository: ProductsRemoteRepositoryImpl): ProductsRemoteRepository

    @Binds
    abstract fun dataSourceRemoteProducts(dataSourceRemoteProducts: DataSourceRemoteProductsImpl): DataSourceRemoteProducts

}