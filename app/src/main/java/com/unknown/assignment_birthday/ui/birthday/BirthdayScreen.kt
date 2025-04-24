package com.unknown.assignment_birthday.ui.birthday

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlin.random.Random
import com.unknown.assignment_birthday.R
import com.unknown.assignment_birthday.ui.birthday.widgets.BirthdayCountWidget
import com.unknown.assignment_birthday.ui.common.CommonBabyFaceWidget
import com.unknown.assignment_birthday.ui.common.CommonIconButtonWidget
import com.unknown.assignment_birthday.utils.rememberImagePicker

@Composable
internal fun BirthdayScreen(
    name: String,
    birthday: String,
    photoUri: String,
    navController: NavController
) {
    val viewModel: BirthdayViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.onInit(name, birthday, photoUri)
    }
    val uiState by viewModel.uiState.collectAsState()

    val screenType =
        remember { BirthdayScreenType.entries[Random.nextInt(BirthdayScreenType.entries.size)] }

    val imagePickerLauncher = rememberImagePicker {
        viewModel.onPhotoPicked(it)
    }

    BirthdayScreen(
        uiState = uiState,
        viewModel = viewModel,
        screenType = screenType,
        imagePickerLauncher = imagePickerLauncher,
        navController = navController,
    )
}

@Composable
fun BirthdayScreen(
    uiState: BirthdayUiState,
    viewModel: BirthdayViewModel,
    screenType: BirthdayScreenType,
    imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>,
    navController: NavController
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imagePaddingBottom = LocalConfiguration.current.screenHeightDp * 0.2
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenType.backgroundColor)
    ) {

        if (!uiState.isScreenshot)
            CommonIconButtonWidget(
                onTap = { navController.popBackStack() },
                icon = Icons.Default.Close,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(dimensionResource(id = R.dimen.birthday_common_icon_button_padding))
            )

        if (!uiState.isScreenshot)
            CommonIconButtonWidget(
                onTap = { viewModel.captureAndShareScreenshot(context) },
                icon = Icons.Default.Share,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(id = R.dimen.birthday_common_icon_button_padding))
            )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.birthday_root_column_padding))
        ) {
            Text(
                text = stringResource(
                    id = R.string.birthday_name,
                    uiState.name.toUpperCase(locale = Locale.current)
                ),
                modifier = Modifier
                    .padding(
                        start = screenWidth * 0.15f,
                        top = dimensionResource(id = R.dimen.birthday_title_padding_top),
                        end = screenWidth * 0.15f
                    ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 21.sp
                )
            )

            BirthdayCountWidget(
                date = viewModel.getImageForAge(uiState.birthday), modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        top = dimensionResource(id = R.dimen.birthday_screen_count_widget_padding_top),
                        bottom = dimensionResource(id = R.dimen.birthday_screen_count_widget_padding_bottom)
                    ),
                text = viewModel.getBirthdayDescription(uiState.birthday)
            )
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(bottom = imagePaddingBottom.dp),
            ) {

                CommonBabyFaceWidget(
                    faceColor = screenType.faceColor,
                    circleColor = screenType.faceContainerColor,
                    borderColor = screenType.faceColor,
                    photoUri = uiState.photoUri,
                    isHideCameraButton = uiState.isScreenshot,
                    onTap = { imagePickerLauncher.launch("image/*") }
                )
            }

        }
        Image(
            painter = painterResource(id = screenType.backgroundImage),
            contentDescription = stringResource(id = R.string.content_description_bg),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp)
                .align(Alignment.BottomStart)
        )

        Image(
            painter = painterResource(id = R.drawable.logo_nanit),
            modifier = Modifier
                .padding(bottom = (imagePaddingBottom.dp - dimensionResource(id = R.dimen.birthday_screen_nanit_logo_padding)))
                .align(Alignment.BottomCenter),
            contentDescription = stringResource(R.string.content_description_logo_image),
        )
    }
}


