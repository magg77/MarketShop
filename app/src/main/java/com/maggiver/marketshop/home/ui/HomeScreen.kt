package com.maggiver.marketshop.home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.Product
import com.maggiver.marketshop.home.data.provider.remote.model.ProductsResponse
import com.maggiver.marketshop.home.presentation.ProductsViewModel

@Composable
fun HomeScreen(
    viewModelProducts: ProductsViewModel = hiltViewModel()
) {

    val uiState = viewModelProducts.uiStateProducts.collectAsState()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFE80000)))
    {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE80000))
        ) { padding ->

            when (val state = uiState.value) {

                is ResourceState.LoadingState -> {
                    Text("Cargando...", modifier = Modifier.padding(padding))
                }

                is ResourceState.SuccessState -> {

                    Log.i("products3", "${state.data}")
                    HomeContent(
                        products = state.data,
                        modifier = Modifier.padding(padding)
                    )


                }

                is ResourceState.FailureState -> {
                    Text("Error: ${state.message}", modifier = Modifier.padding(padding))
                }

            }
        }
    }

}


@Composable
fun HomeContent(
    products: ProductsResponse,
    modifier: Modifier = Modifier
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
        onFavoriteClick = {}
    )
}

@Composable
fun ProductCard(
    product: Product,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White   // ⭐ Card blanca
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()   // Ajusta como quieras
            ) {

                // Imagen principal
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )

                // ⭐ Botón favoritos sobre la imagen
                IconButton (
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = Color.White,
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.4f),
                                shape = CircleShape
                            )
                            .padding(6.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = product.title ?: "",
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(0.dp))

                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}