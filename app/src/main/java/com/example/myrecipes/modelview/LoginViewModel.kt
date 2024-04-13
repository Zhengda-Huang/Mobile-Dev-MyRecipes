package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myrecipes.model.database.User.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    application: Application,
    val userRepository: UserRepository,
): AndroidViewModel(application) {
    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    /**
     * sets the username text to the given value.
     */
    fun setEmailText(value: String) {
        _emailText.value = value
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
        setEmailText("")
        setPasswordText("")
    }

    /**
     * checks if the user credentials are valid in the database
     * if true, clears the current input and returns true
     * if false, displays an error message
     */
    suspend fun isValidCredentials(): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()
        if (emailText.value.isEmpty() || !emailRegex.matches(emailText.value)) {
            _errorMessage.value = "Please enter your email correctly."
        } else if (passwordText.value.isEmpty()) {
            _errorMessage.value = "Please enter your password."
        } else {
            val user = userRepository.getUserByEmail(emailText.value)
            if (user != null) {
                if (user.password == passwordText.value) {
                    clearInput()
                    return true
                } else {
                    _errorMessage.value = "Incorrect password, try again."
                }
            } else {
                _errorMessage.value = "It seems like an account with this email does not exist."
            }
        }
        return false
    }
}