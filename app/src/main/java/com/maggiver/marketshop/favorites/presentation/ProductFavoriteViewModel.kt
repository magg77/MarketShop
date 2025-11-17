package com.maggiver.marketshop.favorites.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.favorites.domain.ProductFavoriteUseCase
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFavoriteViewModel @Inject constructor(
    private val productFavoriteUseCase: ProductFavoriteUseCase
):
    ViewModel() {

    private var _uiStateProductsFavorite: MutableStateFlow<ResourceState<List<ProductDetailResponse>>> =
        MutableStateFlow(ResourceState.LoadingState())
    val uiStateProductsFavorite: StateFlow<ResourceState<List<ProductDetailResponse>>> = _uiStateProductsFavorite.asStateFlow().stateIn(
        scope = viewModelScope,
        initialValue = ResourceState.LoadingState(),
        started = SharingStarted.WhileSubscribed(5000)
    )


    private val _favoriteState = MutableStateFlow<Boolean>(false)
    val favoriteState: MutableStateFlow<Boolean> = _favoriteState


    fun isFavoriteRoom(productId: Int){
        viewModelScope.launch {
            val current = productFavoriteUseCase.getProductFavoriteUseCase(productFavoriteId = productId)
            if (current != null){
                _favoriteState.value = true
            }else{
                _favoriteState.value = false
            }
        }
    }

    fun toggleFavorite(product: ProductDetailResponse) {
        val productId = product.id
        viewModelScope.launch {
            val current = productFavoriteUseCase.getProductFavoriteUseCase(productFavoriteId = productId)
            if (current != null) {
                productFavoriteUseCase.removeProductFavoriteUseCase(product)
                _favoriteState.value = false
            } else {
                productFavoriteUseCase.insertProductFavoriteUseCase(product)
                _favoriteState.value = true
            }
        }
    }

    fun getProductFavoriteRoom(){
        viewModelScope.launch {
            productFavoriteUseCase.getAllProductsFavoriteUseCase()
                .onStart { _uiStateProductsFavorite.value = ResourceState.LoadingState() }
                .catch { it ->
                    _uiStateProductsFavorite.value = ResourceState.FailureState(message = it.message.toString())
                }
                .collect { _uiStateProductsFavorite.value = it }
        }
    }




}