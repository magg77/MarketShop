package com.maggiver.marketshop.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.core.view.components.CircularProgressIndicatorOffers
import com.maggiver.marketshop.core.view.components.ErrorContent
import com.maggiver.marketshop.core.view.components.FullDetailDialog
import com.maggiver.marketshop.core.view.components.ProductCard
import com.maggiver.marketshop.home.data.provider.remote.model.Product
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.home.presentation.ProductsViewModel

@Composable
fun HomeScreen(
    viewModelProducts: ProductsViewModel = hiltViewModel()
) {

    //val uiState = viewModelProducts.uiStateProducts.collectAsState().value
    val uiState = viewModelProducts.uiStateFlowProducts.collectAsStateWithLifecycle().value

    val showDialog = viewModelProducts.showDetailDialog.collectAsState().value
    val uiStateDetailProduct = viewModelProducts.uiStateProductDetail.collectAsState().value

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect (uiState) {
        if (uiState is ResourceState.FailureState) {
            snackbarHostState.showSnackbar(uiState.message)
        }
    }


        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE80000)))
        {innerPadding ->

            when (uiState) {

                is ResourceState.LoadingState -> {
                    //Text("Cargando...", modifier = Modifier.padding(padding))
                    CircularProgressIndicatorOffers(statusLoading = true)
                }

                is ResourceState.SuccessState -> {

                    CircularProgressIndicatorOffers(statusLoading = false)

                    HomeContent(
                        products = uiState.data,
                        modifier = Modifier.padding(innerPadding),
                        onProductClick = { productId ->
                            viewModelProducts.onProductClick(productId = productId)
                        }
                    )

                }

                is ResourceState.FailureState -> {
                    //Text("Error: ${uiState.message}", modifier = Modifier.padding(padding))
                    CircularProgressIndicatorOffers(statusLoading = false)

                    ErrorContent(
                        message = uiState.message,
                        modifier = Modifier.padding(innerPadding),
                        onRetry = {
                            viewModelProducts.productsStateInFlow()
                        }
                    )

                }

            } // when

            if (showDialog){
                FullDetailDialog (
                    uiStateDetailProduct = uiStateDetailProduct,
                    onClose = { viewModelProducts.closeDetailDialog() }
                )
            }

        }




}


@Composable
fun HomeContent(
    products: ProductsResponse,
    modifier: Modifier = Modifier,
    onProductClick: (Int) -> Unit
) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),             // ⭐ Dos columnas
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Título de sección
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {


                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(26.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = "¡Lo mas top!",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,   // Negrilla fuerte
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        // Productos
        items(products.products) { product ->
            ProductCard(
                product = product,
                onProductClick = { productId -> onProductClick(productId) },
                onFavoriteClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val mockProduct = Product(
        id = 1,
        title = "Smartphone Xiaomi Pro Max",
        price = 899.99,
        thumbnail = "https://d2opxh93rbxzdn.cloudfront.net/original/2X/4/40cfa8ca1f24ac29cfebcb1460b5cafb213b6105.png"
    )

    ProductCard(
        product = mockProduct,
        onProductClick = {},
        onFavoriteClick = {}
    )

}



