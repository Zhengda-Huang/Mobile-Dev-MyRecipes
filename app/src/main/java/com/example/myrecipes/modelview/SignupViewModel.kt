package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myrecipes.model.database.User.User
import com.example.myrecipes.model.database.User.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class SignupViewModel(
    application: Application,
    val userRepository: UserRepository
): AndroidViewModel(application) {

    data class UiState(
        val usernameText: String = "",
        val emailText: String = "",
        val passwordText: String = "",
        val errorMessage: String = ""
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    /**
     * sets the username text to the given value.
     */
    fun setUsernameText(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                usernameText = value,
                emailText = currentState.emailText,
                passwordText = currentState.passwordText,
                errorMessage = currentState.errorMessage,
            )
        }
        clearError()
    }

    /**
     * sets the email text to the given value.
     */
    fun setEmailText(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                usernameText = currentState.usernameText,
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
                usernameText = currentState.usernameText,
                emailText = currentState.emailText,
                passwordText = value,
                errorMessage = currentState.errorMessage,
            )
        }
        clearError()
    }

    /**
     * sets the error message to the given value.
     */
    private fun setErrorMessage(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                usernameText = currentState.usernameText,
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
                usernameText = currentState.usernameText,
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
        setUsernameText("")
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

    /**
     * checks if the information is valid to sign up
     * if true, clears the current input and returns true
     * if false, displays an error message
     */
    suspend fun isValidSignUp(): Boolean {
        val email = _uiState.value.emailText
        val username = _uiState.value.usernameText
        val password = _uiState.value.passwordText

        val emailRegex = "^[A-Za-z0-9]*([@])(.+)(\\.)(.+)".toRegex()
        if (email.isEmpty() || !emailRegex.matches(email)) {
            setErrorMessage("Please use a valid email to sign up.")
        } else if (username.length < 3 || username.length > 16) {
            setErrorMessage("Username should be 3 to 16 characters.")
        } else if (password.length < 6) {
            setErrorMessage("Password should be at least 6 characters.")
        } else {
            val user = userRepository.getUserByEmail(email)
            if (user != null) {
                setErrorMessage("User with the given email already exists!")
            } else {
                userRepository.insertUser(
                    User(
                        username = username,
                        password = password,
                        email = email,
                    )
                )
                clearInput()
                return true
            }
        }
        return false
    }
}