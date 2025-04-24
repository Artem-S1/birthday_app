package com.unknown.assignment_birthday.ui.main

import android.net.Uri
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.unknown.assignment_birthday.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    fun onNameChange(newName: TextFieldValue) {
        _uiState.update { current ->
            val newState = current.copy(name = newName)
            newState.copy(isMoveNextAvailable = isMoveNextEnabled(newState))
        }
    }

    fun onBirthdaySelected(newBirthday: String) {
        _uiState.update { current ->
            val newState = current.copy(birthday = newBirthday)
            newState.copy(isMoveNextAvailable = isMoveNextEnabled(newState))
        }
    }

    fun onPhotoPicked(uri: Uri?) {
        _uiState.update { current ->
            val newState = current.copy(photoUri = uri)
            newState.copy(isMoveNextAvailable = isMoveNextEnabled(newState))
        }
    }

    fun navigateToBirthdayScreen(navController: NavController) {
        val state = _uiState.value
        val route = Route.Birthday.createRoute(
            name = state.name.text,
            birthday = Uri.encode(state.birthday),
            photoUri = Uri.encode(state.photoUri.toString())
        )

        navController.navigate(route)
    }

    private fun isMoveNextEnabled(state: MainUiState): Boolean {
        return state.name.text.isNotBlank() &&
                state.birthday.isNotEmpty() &&
                state.photoUri != null
    }
}


