package com.unknown.assignment_birthday.ui.birthday

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unknown.assignment_birthday.utils.captureScreenshot
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import com.unknown.assignment_birthday.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.unknown.assignment_birthday.utils.saveScreenshot
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.time.format.DateTimeFormatter

@HiltViewModel
class BirthdayViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(BirthdayUiState())
    val uiState: StateFlow<BirthdayUiState> = _uiState

    fun onInit(
        name: String,
        birthday: String,
        photoUri: String,
    ) {
        _uiState.update { current ->
            current.copy(
                photoUri = photoUri.toUri(),
                name = name,
                birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d/M/yyyy"))
            )
        }
    }

    fun onPhotoPicked(uri: Uri?) {
        _uiState.update { currentState ->
            currentState.copy(photoUri = uri)
        }
    }

    fun getImageForAge(birthday: LocalDate): Pair<Int, Int?> {
        val age = getAge(birthday)

        return if (age < 10) {
            Pair(numberImages[age], null)
        } else {
            val firstDigit = age / 10
            val secondDigit = age % 10

            Pair(
                numberImages.getOrElse(firstDigit) { numberImages.last() },
                numberImages.getOrElse(secondDigit) { numberImages.last() }
            )
        }
    }

    fun getBirthdayDescription(birthday: LocalDate): String {
        val today = LocalDate.now()

        return if (ChronoUnit.MONTHS.between(birthday, today) < 12) {
            val monthsBetween = ChronoUnit.MONTHS.between(birthday, today)
            "MONTH${if (monthsBetween > 1) "S" else ""} OLD!"
        } else {
            val yearsBetween = ChronoUnit.YEARS.between(birthday, today)
            "YEAR${if (yearsBetween > 1) "S" else ""} OLD!"
        }
    }


    fun captureAndShareScreenshot(context: Context) {
        toggleScreenshot(true)

        viewModelScope.launch {
            delay(200)

            val bitmap = captureScreenshot(context)
            saveScreenshot(context, bitmap)

            shareContent(context)
        }
    }

    private fun shareContent(context: Context) {

        val file =
            File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "screenshots.png")

        val fileUri: Uri = FileProvider.getUriForFile(
            context,
            "com.unknown.assignment_birthday.provider",
            file
        )

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, fileUri)
            type = "image/png"
        }

        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        toggleScreenshot(false)

    }

    private fun toggleScreenshot(isScreenshot: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isScreenshot = isScreenshot)
        }
    }


    private fun getAge(birthday: LocalDate): Int {
        val today = LocalDate.now()

        return if (ChronoUnit.MONTHS.between(birthday, today) < 12) {
            ChronoUnit.MONTHS.between(birthday, today).toInt()
        } else {
            ChronoUnit.YEARS.between(birthday, today).toInt()
        }
    }

    private val numberImages = listOf(
        R.drawable.ic_number_zero,
        R.drawable.ic_number_one,
        R.drawable.ic_number_two,
        R.drawable.ic_number_three,
        R.drawable.ic_number_four,
        R.drawable.ic_number_five,
        R.drawable.ic_number_six,
        R.drawable.ic_number_seven,
        R.drawable.ic_number_eight,
        R.drawable.ic_number_nine,
        R.drawable.ic_number_ten,
        R.drawable.ic_number_eleven,
        R.drawable.ic_number_twelve
    )
}
