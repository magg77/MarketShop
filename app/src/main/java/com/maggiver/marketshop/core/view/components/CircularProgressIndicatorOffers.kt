package com.maggiver.marketshop.core.view.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.maggiver.marketshop.R

@Composable
fun CircularProgressIndicatorOffers(statusLoading: Boolean) {
    if (statusLoading) {

        val preloaderLottieComposition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                R.raw.animation_loading
            )
        )
        val preloaderProgress by animateLottieCompositionAsState(
            composition = preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )
        LottieAnimation(composition = preloaderLottieComposition, progress = { preloaderProgress })
    }
}