package com.unknown.assignment_birthday.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.unknown.assignment_birthday.ui.birthday.BirthdayScreen
import com.unknown.assignment_birthday.ui.main.MainScreen

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(Route.Main.route) {
        MainScreen(navController)
    }
}

fun NavGraphBuilder.birthdayGraph(navController: NavHostController) {
    composable(
        route = Route.Birthday.fullRoute,
        arguments = listOf(
            navArgument(Route.Birthday.NAME_ARG) { type = NavType.StringType },
            navArgument(Route.Birthday.BIRTHDAY_ARG) { type = NavType.StringType },
            navArgument(Route.Birthday.PHOTO_URI_ARG) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString(Route.Birthday.NAME_ARG).orEmpty()
        val birthday = backStackEntry.arguments?.getString(Route.Birthday.BIRTHDAY_ARG).orEmpty()
        val photoUri = backStackEntry.arguments?.getString(Route.Birthday.PHOTO_URI_ARG).orEmpty()

        BirthdayScreen(name, birthday, photoUri, navController)
    }
}