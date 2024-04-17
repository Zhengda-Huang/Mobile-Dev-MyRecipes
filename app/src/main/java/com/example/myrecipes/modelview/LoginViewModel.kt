package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myrecipes.model.database.User.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class LoginViewModel(
    application: Application,
    val userRepository: UserRepository,
): AndroidViewModel(application) {
    data class UiState(
        val emailText: String = "",
        val passwordText: String = "",
        val errorMessage: String = ""
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

//    private val _emailText = MutableStateFlow("")
//    val emailText: StateFlow<String> = _emailText
//
//    private val _passwordText = MutableStateFlow("")
//    val passwordText: StateFlow<String> = _passwordText
//
//    private val _errorMessage = MutableStateFlow("")
//    val errorMessage: StateFlow<String> = _errorMessage

    /**
     * sets the username text to the given value.
     */
    fun setEmailText(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailText = value,
                passwordText = currentState.passwordText,
                errorMessage = currentState.errorMessage,
            )
        }
        clearError()
    }

    /**
     * sets the password text to the given value.
     */
    fun setPasswordText(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailText = currentState.emailText,
                passwordText = value,
                errorMessage = currentState.errorMessage,
            )
        }
        clearError()
    }

    private fun setErrorText(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailText = currentState.emailText,
                passwordText = currentState.passwordText,
                errorMessage = value,
            )

        }
    }

    /**
     * clear error text
     */
    private fun clearError() {
        _uiState.update { currentState ->
            currentState.copy(
                emailText = currentState.emailText,
                passwordText = currentState.passwordText,
                errorMessage = "",
            )
        }
    }

    /**
     * clear input text
     */
    private fun clearInput() {
        setEmailText("")
        setPasswordText("")
    }

    /**
     * clear all strings
     */
    fun clearAll() {
        clearInput()
        clearError()
    }

    suspend fun getUserId(): Long{
        val user = userRepository.getUserByEmail(_uiState.value.emailText)
        if (user != null) {
            return user.userId
        }
        return 0
    }

    /**
     * checks if the user credentials are valid in the database
     * if true, clears the current input and returns true
     * if false, displays an error message
     */
    suspend fun isValidCredentials(): Boolean {
        val email = _uiState.value.emailText
        val password = _uiState.value.passwordText

        val emailRegex = "^[A-Za-z0-9]*([@])(.+)(\\.)(.+)".toRegex()
        if (email.isEmpty() || !emailRegex.matches(email)) {
            setErrorText("Please enter your email correctly.")
        } else if (password.isEmpty()) {
            setErrorText("Please enter your password.")
        } else {
            val user = userRepository.getUserByEmail(email)
            if (user != null) {
                if (user.password == password) {
                    clearInput()
                    return true
                } else {
                    setErrorText("Incorrect password, try again.")
                }
            } else {
                setErrorText("It seems like an account with this email does not exist.")
            }
        }
        return false
    }
}