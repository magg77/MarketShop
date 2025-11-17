package com.maggiver.marketshop.core.view.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse
import com.maggiver.marketshop.home.presentation.ProductsViewModel

@Composable
fun FullDetailDialog(
    uiStateDetailProduct: ResourceState<ProductDetailResponse>?,
    onClose: () -> Unit,
    viewModelProducts: ProductsViewModel = hiltViewModel()
) {
    Dialog (
        onDismissRequest = onClose,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {

        AnimatedVisibility (
            visible = true,
            enter = fadeIn(animationSpec = tween(500)) +
                    scaleIn(initialScale = 0.90f, animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500)) +
                    scaleOut(targetScale = 0.90f, animationSpec = tween(500))
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(0.dp)
            ) {

                when (uiStateDetailProduct) {

                    is ResourceState.LoadingState -> {
                        Log.i(
                            "detailproduct9",
                            "sdjflkasjdflkasjdflajdflajsdlfajdslfjalksdfjalksdfjjlasdj"
                        )
                        CircularProgressIndicatorOffers(statusLoading = true)
                    }

                    is ResourceState.SuccessState -> {
                        CircularProgressIndicatorOffers(statusLoading = false)
                        DetailContent(uiStateDetailProduct.data, onClose)
                    }

                    is ResourceState.FailureState -> {
                        CircularProgressIndicatorOffers(statusLoading = false)
                        ErrorContent(
                            message = uiStateDetailProduct.message,
                            onRetry = {
                                viewModelProducts.closeDetailDialog()
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    product: ProductDetailResponse,
    onClose: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Cerrar
        IconButton (
            onClick = onClose,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
        }

        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = product.title ?: "Sin titulo",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(24.dp)
        )

        RatingBarAnimada(
            rating = product.rating ?: 0.0,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = product.description ?: "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    maxRating: Int = 5
) {
    val filledStars = rating.toInt()
    val halfStar = (rating - filledStars) >= 0.5

    Row (verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        // Estrellas llenas
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107), // Amarillo dorado
                modifier = Modifier.size(22.dp)
            )
        }

        // Media estrella
        if (halfStar) {
            Icon(
                imageVector = Icons.Default.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(22.dp)
            )
        }

        // Estrellas vacías
        repeat(maxRating - filledStars - if (halfStar) 1 else 0) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(22.dp)
            )
        }

        // Texto del rating
        Text(
            text = "  ${String.format("%.1f", rating)}",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Gray
        )
    }
}

@Composable
fun RatingBarAnimada(
    rating: Double,
    modifier: Modifier = Modifier,
    maxRating: Int = 5
) {
    val animatedRating = animateFloatAsState(
        targetValue = rating.toFloat(),
        animationSpec = tween(durationMillis = 800, easing = LinearOutSlowInEasing),
        label = ""
    )

    val filledStars = animatedRating.value.toInt()
    val halfStar = (animatedRating.value - filledStars) >= 0.5f

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(24.dp)
            )
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.Default.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(24.dp)
            )
        }

        repeat(maxRating - filledStars - if (halfStar) 1 else 0) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "  ${String.format("%.1f", rating)}",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Gray
        )
    }
}
