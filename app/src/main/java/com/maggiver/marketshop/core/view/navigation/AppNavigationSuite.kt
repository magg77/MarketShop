package com.maggiver.marketshop.core.view.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@PreviewScreenSizes
@Composable
fun AppNavigationSuite() {

    val contextLocal = LocalContext.current
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navItems.forEach { item ->

                val selected = currentRoute == item.route.route

                item(
                    selected = selected,
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount > 0) {
                                    Badge { Text("${item.badgeCount}") }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        }
                    },
                    label = { Text(item.label) },
                    onClick = {
                        if (!selected) {
                            navController.navigate(item.route.route) {
                                restoreState = true
                                launchSingleTop = true
                                popUpTo(AppRoute.Home.route) { saveState = true }
                            }
                        }
                    }
                )
            }
        }
    ) {
        AppNavHost(
            navController = navController
        )
    }
}