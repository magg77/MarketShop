package com.maggiver.marketshop.favorites.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.core.view.components.CircularProgressIndicatorOffers
import com.maggiver.marketshop.core.view.components.EmptyFavoriteView
import com.maggiver.marketshop.favorites.presentation.ProductFavoriteViewModel
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse

@Composable
fun FavoriteScreen(
    viewModelFavorite: ProductFavoriteViewModel = hiltViewModel()
) {

    val uiStateFavorites = viewModelFavorite.uiStateProductsFavoriteLocal.collectAsStateWithLifecycle().value
    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect (uiStateFavorites) {
        if (uiStateFavorites is ResourceState.FailureState) {
            snackbarHostState.showSnackbar(uiStateFavorites.message)
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE80000))) { padding ->

            when (uiStateFavorites) {

                is ResourceState.LoadingState -> {
                    //Text("Cargando...", modifier = Modifier.padding(padding))
                    CircularProgressIndicatorOffers(statusLoading = true)
                }

                is ResourceState.SuccessState -> {

                    CircularProgressIndicatorOffers(statusLoading = false)

                    val list = uiStateFavorites.data
                    if (list.isEmpty()) {
                        EmptyFavoriteView(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        )
                    } else {
                        HomeContentFavorite(
                            product = list,
                            modifier = Modifier.padding(padding),
                            onRemoveFavorite = { product ->
                                viewModelFavorite.removeFavorite(product)
                            }
                        )
                    }



                    HomeContentFavorite(
                        product = uiStateFavorites.data,
                        modifier = Modifier.padding(padding),
                        onRemoveFavorite = {product ->
                            viewModelFavorite.removeFavorite(product = product)
                        }
                    )

                }

                is ResourceState.FailureState -> {
                    //Text("Error: ${uiState.message}", modifier = Modifier.padding(padding))
                    CircularProgressIndicatorOffers(statusLoading = false)
                }

            } // when


    }

}

@Composable
fun HomeContentFavorite(
    product: List<ProductDetailResponse>,
    modifier: Modifier = Modifier,
    onRemoveFavorite: (ProductDetailResponse) -> Unit
) {
    LazyColumn (modifier = modifier.fillMaxSize()) {

        items(product) { product ->

            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                    //.clickable{ onProductClick(product.id)}, //captura el id del product
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White   // ⭐ Card blanca
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ){

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {

                    AsyncImage(
                        model = product.thumbnail,
                        contentDescription = product.title,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column (modifier = Modifier.weight(1f).padding(top = 16.dp)) {

                        Text(
                            text = product.title ?: "Sin título",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = "Precio: $${product.price ?: 0.0}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF009900)
                        )
                    }

                    // icon eliminar favorite product
                    IconButton (
                        onClick = { onRemoveFavorite(product) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Quitar de favoritos",
                            tint = Color.Red
                        )
                    }

                }

            }


        }
    }
}

