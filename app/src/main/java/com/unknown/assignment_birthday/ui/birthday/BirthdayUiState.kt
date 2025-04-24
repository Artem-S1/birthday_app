package com.unknown.assignment_birthday.ui.birthday

import android.net.Uri
import java.time.LocalDate

data class BirthdayUiState(
    val name: String = "",
    val birthday: LocalDate = LocalDate.now(),
    var photoUri: Uri? = null,
    var isScreenshot: Boolean = false,
)
