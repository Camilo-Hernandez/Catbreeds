package com.camihruiz24.catbreeds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camihruiz24.catbreeds.ui.breed_detail.CatBreedDetailScreen
import com.camihruiz24.catbreeds.ui.breeds_list.ListScreen
import com.camihruiz24.catbreeds.ui.favorites.FavoritesList
import com.camihruiz24.catbreeds.ui.favorites.fixedFavorites

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
) {
    val startDestination: String = Route.ListScreen.route
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Route.ListScreen.route) {
            ListScreen(
                onNavigateToDetail = { item ->
                    /**
                     * Este composable (ListScreen) envía un argumento al composable Detail.
                     * El nombre del argumento (siendo opcional pues obligatorio no necesita nombre, no más un slash "/")
                     * en la ruta del transmisor debe corresponder con lo que acepta el composable receptor.
                     * Para argumentos nombrados, en lugar de / se pone ``?NombreArgumento=``, y se pone luego el nombre de dicho argumento.
                     * Por automatizar un poco, se ha creado una lista de nombres de argumentos en la clase [NavigationArg] para las pantallas
                     * que reciben argumentos.
                     */
                    navController.navigate(Route.Detail.createNavRoute(item.id))
                }
            )
        }
        composable(
            route = Route.Detail.baseRoute + "?itemId={itemId}",
            arguments = Route.Detail.arguments
        ) {
            CatBreedDetailScreen(
                onAddToFavorites = {
                    navController.navigate(Route.Favorites.route)
                }
            )
        }
        composable(
            route = Route.Favorites.baseRoute,
        ){
            FavoritesList(favorites = fixedFavorites)
        }
    }

}
