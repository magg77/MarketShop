package com.maggiver.marketshop.home.data.repository.remote

import android.app.Application
import android.content.Context
import android.util.Log
import com.maggiver.marketshop.core.utils.ConnectionManager
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.home.data.provider.remote.server.DataSourceRemoteProducts
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class ProductsRemoteRepositoryImpl @Inject constructor(
    private val dataSourceRemoteProducts: DataSourceRemoteProducts,
    @param:ApplicationContext private val context: Context,
    private val application: Application
):
ProductsRemoteRepository{ // class

    override suspend fun getProductsRemoteRepository(): Flow<ResourceState<ProductsResponse>> =
        channelFlow {

        // verificar conexion de red
        if (!ConnectionManager.isNetworkAvailable(application.applicationContext)) {
            send(ResourceState.FailureState("Verifica tu conexión de red"))
            return@channelFlow      // Detiene el flujo aquí mismo
        }

        // emitir estado de carga
        send(ResourceState.LoadingState())

        when(val responseProducts = dataSourceRemoteProducts.getProductsRemote()) {

            is ResourceState.SuccessState -> {
                Log.i("products3", "Repository = ${responseProducts.data}")
                send(ResourceState.SuccessState(responseProducts.data))
            }

            is ResourceState.FailureState -> {
                send(ResourceState.FailureState(responseProducts.message))
            }

            else -> {
                send(ResourceState.FailureState(message = "Error desconocido"))
            }

        } // when

    } // channelFlow - getProductsRemoteRepository()

    override suspend fun getProductDetailRemoteRepository(productId: Int): Flow<ResourceState<ProductDetailResponse>> =
        channelFlow {

            // verificar conexion de red
            if (!ConnectionManager.isNetworkAvailable(application.applicationContext)) {
                send(ResourceState.FailureState("Verifica tu conexión de red"))
                return@channelFlow      // Detiene el flujo aquí mismo
            }

            // emitir estado de carga
            send(ResourceState.LoadingState())

            when(val responseProducts = dataSourceRemoteProducts.getProductDetailRemote(productId = productId)) {

                is ResourceState.SuccessState -> {
                    send(ResourceState.SuccessState(responseProducts.data))
                }

                is ResourceState.FailureState -> {
                    send(ResourceState.FailureState(responseProducts.message))
                }

                else -> {
                    send(ResourceState.FailureState(message = "Error desconocido"))
                }

            } // when

        } // channelFlow - getProductDetailRemoteRepository()


}