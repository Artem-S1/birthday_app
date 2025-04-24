package com.unknown.assignment_birthday.ui.birthday

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
internal fun BirthdayScreen(
    name: String,
    birthday: String,
    photoUri: String,
    navController: NavController
) {
    val viewModel: BirthdayViewModel = hiltViewModel()


    BirthdayScreen(
        viewModel = viewModel,
        navController = navController,
    )
}

@Composable
fun BirthdayScreen(
    viewModel: BirthdayViewModel,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {}
}


