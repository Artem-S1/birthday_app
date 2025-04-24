package com.unknown.assignment_birthday.utils

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.Calendar

@Composable
fun rememberDatePicker(
    context: Context,
    onDateSelected: (String) -> Unit
): DatePickerDialog {
    val calendar = remember { Calendar.getInstance() }
    return remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected("$dayOfMonth/${month + 1}/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}

@Composable
fun rememberImagePicker(onImagePicked: (Uri?) -> Unit) =
    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onImagePicked(uri)
    }
