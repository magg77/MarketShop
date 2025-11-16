package com.maggiver.marketshop.core.view.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")
    data object Favorites : AppRoute("favorite")
    data object Profile : AppRoute("profile")
}

