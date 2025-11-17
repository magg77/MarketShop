package com.maggiver.marketshop.core.view.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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