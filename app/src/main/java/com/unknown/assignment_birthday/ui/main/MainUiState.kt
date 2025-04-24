package com.unknown.assignment_birthday.ui.main

import android.net.Uri
import androidx.compose.ui.text.input.TextFieldValue

data class MainUiState(
    val name: TextFieldValue = TextFieldValue(""),
    val birthday: String = "",
    val photoUri: Uri? = null,
    val isMoveNextAvailable: Boolean = false
)
