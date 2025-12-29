package com.maggiver.marketshop.core.view.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maggiver.marketshop.favorites.ui.FavoriteScreen
import com.maggiver.marketshop.home.ui.HomeScreen
import com.maggiver.marketshop.profile.ui.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppRoute.Home.route,
        enterTransition = {
            slideInHorizontally { it / 3 } + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally { -it / 3 } + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally { -it / 3 } + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally { it / 3 } + fadeOut()
        }
    )  {

        composable (AppRoute.Home.route) {
            HomeScreen()
        }

        composable(AppRoute.Favorites.route) {
            FavoriteScreen()
        }

        composable(AppRoute.Profile.route) {
            ProfileScreen()
        }
    }
}