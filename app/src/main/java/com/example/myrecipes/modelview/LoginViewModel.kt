package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val _usernameText = MutableStateFlow("")
    val usernameText: StateFlow<String> = _usernameText

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    /**
     * sets the username text to the given value.
     */
    fun setUsernameText(value: String) {
        _usernameText.value = value
        clearError()
    }

    /**
     * sets the password text to the given value.
     */
    fun setPasswordText(value: String) {
        _passwordText.value = value
        clearError()
    }

    /**
     * clear error text
     */
    private fun clearError() {
        _errorMessage.value = ""
    }

    /**
     * clear input text
     */
    private fun clearInput() {
        _usernameText.value = ""
        _passwordText.value = ""
    }

    /**
     * checks if the user credentials are valid in the database
     * if true, clears the current input and returns true
     * if false, displays an error message
     */
    fun isValidCredentials(): Boolean {
        if (usernameText.value == "admin"
            && passwordText.value == "admin") {
            clearInput()
            return true
        } else if (usernameText.value.isEmpty() || passwordText.value.isEmpty()) {
            _errorMessage.value = "Please enter your username AND password!"
        } else {
            _errorMessage.value = "Invalid credentials, Try again!"
            clearInput()
        }
        return false
    }
}