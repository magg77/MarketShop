package com.maggiver.marketshop.core.view.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.maggiver.marketshop.core.valueObjects.ResourceState
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse

@Composable
fun FullDetailDialog(
    uiStateDetailProduct: ResourceState<ProductDetailResponse>?,
    onClose: () -> Unit
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
                            onRetry = {},
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
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = product.description ?: "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}