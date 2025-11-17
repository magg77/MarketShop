package com.maggiver.marketshop.home.data.provider.remote.server

import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.StreamResetException
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DataSourceRemoteProductsImpl @Inject constructor(private val webService: DummyJsonWebService):
DataSourceRemoteProducts { // class

    // get prodcut
    override suspend fun getProductsRemote(): ResourceState<ProductsResponse> {

        return withContext(Dispatchers.IO) {

            try {

                val responseProducts =  webService.getProducts()

                if (responseProducts.isSuccessful){
                    val body = responseProducts.body()

                    if (body != null) {
                        ResourceState.SuccessState(data = body) // Éxito con datos
                    } else {
                        ResourceState.FailureState("Estamos trabajando para solucionarlo")
                    }

                } else {

                    val errorBody = responseProducts.errorBody()?.string()

                    // ⚠️ Manejar códigos de error HTTP específicos
                    val errorMessage = when (responseProducts.code()) {
                        400 -> errorBody ?: "Error en la solicitud"
                        401 -> errorBody ?: "No autorizado (Unauthorized)"
                        403 -> errorBody ?: "Prohibido (Forbidden)"
                        404 -> errorBody ?: "Recurso no encontrado (Not Found)"
                        500 -> errorBody ?: "Error interno del servidor"
                        else -> "Error desconocido: ${responseProducts.code()}"
                    }

                    ResourceState.FailureState(errorMessage)

                }
            } catch (e: IOException) {
                ResourceState.FailureState("Error de red: ${e.message}") // Sin conexión o timeout
            } catch (e: HttpException) {
                ResourceState.FailureState("Error HTTP ${e.code()}: ${e.message()}") // Errores HTTP (400, 500, etc.)
            } catch (e: StreamResetException) {
                ResourceState.FailureState("Conexión interrumpida: ${e.message}") // Errores de streaming en HTTP2
            } catch (e: Exception) {
                ResourceState.FailureState("Error desconocido: ${e.localizedMessage}")
            }


        } // withContext

    } // getProductsRemote()


    // get product detail
    override suspend fun getProductDetailRemote(productId: Int): ResourceState<ProductDetailResponse> {

        return withContext(Dispatchers.IO) {

            try {

                val responseProducts =  webService.getProductDetail(id = productId)

                if (responseProducts.isSuccessful){
                    val body = responseProducts.body()

                    if (body != null) {
                        ResourceState.SuccessState(data = body) // Éxito con datos
                    } else {
                        ResourceState.FailureState("Estamos trabajando para solucionarlo")
                    }

                } else {

                    val errorBody = responseProducts.errorBody()?.string()

                    // ⚠️ Manejar códigos de error HTTP específicos
                    val errorMessage = when (responseProducts.code()) {
                        400 -> errorBody ?: "Error en la solicitud"
                        401 -> errorBody ?: "No autorizado (Unauthorized)"
                        403 -> errorBody ?: "Prohibido (Forbidden)"
                        404 -> errorBody ?: "Recurso no encontrado (Not Found)"
                        500 -> errorBody ?: "Error interno del servidor"
                        else -> "Error desconocido: ${responseProducts.code()}"
                    }

                    ResourceState.FailureState(errorMessage)

                }
            } catch (e: IOException) {
                ResourceState.FailureState("Error de red: ${e.message}") // Sin conexión o timeout
            } catch (e: HttpException) {
                ResourceState.FailureState("Error HTTP ${e.code()}: ${e.message()}") // Errores HTTP (400, 500, etc.)
            } catch (e: StreamResetException) {
                ResourceState.FailureState("Conexión interrumpida: ${e.message}") // Errores de streaming en HTTP2
            } catch (e: Exception) {
                ResourceState.FailureState("Error desconocido: ${e.localizedMessage}")
            }


        } // withContext


    } // getProductDetailRemote()

}