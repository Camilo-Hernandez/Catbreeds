package com.camihruiz24.catbreeds.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

enum class NavigationArg(val key: String, val navType: NavType<*>, val defaultValue: Any) {
    // Solamente la pantalla de Detail tiene un argumento que es el ID del item
    Id(key = "itemId", navType = NavType.StringType, defaultValue = "birm"); // TODO: Extraer el valor por defecto para que sea parametrizable
}

sealed class Route(
    val baseRoute: String,
    private val navigationArgs: List<NavigationArg> = emptyList(),
) {

    val route : String = run {
        val argKeys = navigationArgs.map {
            it.key
        }
        listOf(baseRoute)
            .plus("{$argKeys}=$argKeys")
            .joinToString("?")
    }

    val arguments : List<NamedNavArgument> = navigationArgs.map {
        navArgument(it.key) {
            type = it.navType
            defaultValue = it.defaultValue
        }
    }

    object ListScreen : Route(baseRoute = "list")
    object Detail : Route(baseRoute = "detail", listOf(NavigationArg.Id)){
        fun createNavRoute(itemId: String) = "$baseRoute?${NavigationArg.Id.key}=$itemId"
    }
}