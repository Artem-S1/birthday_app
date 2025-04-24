package com.unknown.assignment_birthday.ui.main

import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unknown.assignment_birthday.R
import com.unknown.assignment_birthday.ui.main.widgets.MainPrimaryButtonWidget
import com.unknown.assignment_birthday.utils.rememberDatePicker
import com.unknown.assignment_birthday.utils.rememberImagePicker
import com.unknown.assignment_birthday.ui.common.CommonBabyFaceWidget

@Composable
internal fun MainScreen(
    navController: NavController,
) {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val datePicker = rememberDatePicker(LocalContext.current) {
        viewModel.onBirthdaySelected(it)
    }

    val imagePickerLauncher = rememberImagePicker {
        viewModel.onPhotoPicked(it)
    }

    MainScreen(
        navController = navController,
        viewModel = viewModel,
        uiState = uiState,
        datePicker = datePicker,
        imagePickerLauncher = imagePickerLauncher,
    )
}

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    uiState: MainUiState,
    datePicker: DatePickerDialog,
    imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.main_root_column_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.main_root_column_vertical_arrangement)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.app_name),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.main_app_name_vertical_padding)),
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = uiState.name,
            onValueChange = viewModel::onNameChange,
            label = { Text(stringResource(R.string.name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        MainPrimaryButtonWidget(
            text = if (uiState.birthday.isEmpty()) stringResource(R.string.pick_birthday)
            else stringResource(R.string.birthday_prefix, uiState.birthday),
            onClick = { datePicker.show() })

        CommonBabyFaceWidget(
            faceColor = colorResource(id = R.color.colorBabyFaceBlue),
            circleColor = colorResource(id = R.color.colorBabyFaceContainerBlue),
            borderColor = colorResource(id = R.color.colorBabyFaceBorderBlue),
            photoUri = uiState.photoUri,
            onTap = { imagePickerLauncher.launch("image/*") }
        )

        MainPrimaryButtonWidget(
            text = stringResource(R.string.show_birthday_screen),
            enabled = uiState.isMoveNextAvailable,
            containerColor = colorResource(id = R.color.colorSkyGreen),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.main_navigate_to_birthday_button_padding)),
            onClick = { viewModel.navigateToBirthdayScreen(navController) })
    }
}


