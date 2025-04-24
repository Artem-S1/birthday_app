package com.unknown.assignment_birthday.ui.navigation

internal sealed interface Route {
    val route: String

    data object Main : Route {
        override val route = "main"
    }

    data object Birthday : Route {
        override val route = "birthday"

        const val NAME_ARG = "name"
        const val BIRTHDAY_ARG = "birthday"
        const val PHOTO_URI_ARG = "photoUri"

        val fullRoute = "$route/{$NAME_ARG}/{$BIRTHDAY_ARG}/{$PHOTO_URI_ARG}"

        fun createRoute(name: String, birthday: String, photoUri: String): String {
            return "$route/$name/$birthday/$photoUri"
        }
    }
}
