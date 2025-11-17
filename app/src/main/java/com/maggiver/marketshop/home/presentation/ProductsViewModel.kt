package com.maggiver.marketshop.home.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.home.domain.ProductsRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCaseProducts: ProductsRemoteUseCase
): ViewModel() {

    //restoreState = true
    // collectAsState() vs collect()
    // StateFlow

    private var _uiStateProducts = MutableStateFlow<ResourceState<ProductsResponse>>(ResourceState.LoadingState())
    val uiStateFlowProducts: StateFlow<ResourceState<ProductsResponse>> = _uiStateProducts.asStateFlow().stateIn(
        scope = viewModelScope,
        initialValue = ResourceState.LoadingState(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _selectedProductId = MutableStateFlow<Int?>(null)
    val selectedProductId: StateFlow<Int?> = _selectedProductId

    private val _showDetailDialog = MutableStateFlow(false)
    val showDetailDialog: StateFlow<Boolean> = _showDetailDialog

    private var _uiStateProductDetail = MutableStateFlow<ResourceState<ProductDetailResponse>>(ResourceState.LoadingState())
    val uiStateProductDetail: StateFlow<ResourceState<ProductDetailResponse>> = _uiStateProductDetail

    init {
        //getProductsViewModel()
        //getProductsRemote()
        productsStateInFlow()
    }

    fun getProductsViewModel() = viewModelScope.launch {
        useCaseProducts.getProductsRemoteUseCase()
            .onEach { _uiStateProducts.value = it }
            .launchIn(viewModelScope)
    }


    fun productsStateInFlow(){
        viewModelScope.launch {
            useCaseProducts.getProductsRemoteUseCase()
                .onStart { _uiStateProducts.value = ResourceState.LoadingState() }
                .catch { it ->
                    _uiStateProducts.value = ResourceState.FailureState(message = it.message.toString())
                }
                .collect { _uiStateProducts.value = it }
        }
    }


    fun onProductClick(productId: Int) {
        _selectedProductId.value = productId
        fetchProductDetails(productId = productId)
    }

    fun fetchProductDetails(productId: Int) {

        _showDetailDialog.value = true

        viewModelScope.launch {
            useCaseProducts.getProductDetailUseCase(productId = productId)
                .collectLatest { detailProduct ->
                    _uiStateProductDetail.value = detailProduct
                }
        }
    }

    fun closeDetailDialog() {
        _showDetailDialog.value = false
        _uiStateProductDetail.value = ResourceState.LoadingState()
    }








}