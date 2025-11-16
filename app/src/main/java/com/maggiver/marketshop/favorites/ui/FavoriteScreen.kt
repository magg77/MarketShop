package com.maggiver.marketshop.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteScreen() {


    Scaffold(modifier = Modifier.background(Color(0xFF4FD014)).fillMaxSize().systemBarsPadding()) { innerPadding ->
        Greeting(
            name = "Favorite",
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD01465))
                .padding(innerPadding)
        )
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier.background(Color(0xFF212A29))
    )
}