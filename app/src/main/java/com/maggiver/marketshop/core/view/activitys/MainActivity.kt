package com.maggiver.marketshop.core.view.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.maggiver.marketshop.core.view.navigation.AppNavigationSuite
import com.maggiver.marketshop.core.view.theme.MarketShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MarketShopTheme {

                AppNavigationSuite()

            }
        }
    }
}


