package com.maggiver.marketshop.core.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.maggiver.marketshop.R


@Composable
fun EmptyFavoriteView(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.favorites), // ⭐ tu imagen aquí
            contentDescription = "Sin favoritos",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "No tienes productos favoritos aún",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}
