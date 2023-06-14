package com.camihruiz24.catbreeds.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camihruiz24.catbreeds.feature_cat_detail.presentation.CatDetailScreen
import com.camihruiz24.catbreeds.feature_list.presentation.HomeScreen
import com.camihruiz24.catbreeds.feature_list.presentation.CatsHomeViewModel

sealed class Routes(val route: String) {
    object Home : Routes(route = "home")
    object CatDetail : Routes(route = "catDetail")
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val startDestination: String = Routes.Home.route
    val navController: NavHostController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Routes.Home.route,
        ) {
            val catsHomeViewModel = hiltViewModel<CatsHomeViewModel>()
            HomeScreen(
                homeViewModel = catsHomeViewModel
            ) {
                navController.navigate(Routes.CatDetail.route)
            }
        }
        composable(route = Routes.CatDetail.route) {
            CatDetailScreen(onAddToFavorites = {})
        }
    }
}