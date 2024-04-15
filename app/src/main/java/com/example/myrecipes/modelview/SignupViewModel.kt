package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myrecipes.model.database.User.User
import com.example.myrecipes.model.database.User.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel(
    application: Application,
    val userRepository: UserRepository
): AndroidViewModel(application) {
    private val _usernameText = MutableStateFlow("")
    val usernameText: StateFlow<String> = _usernameText

    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText

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
     * sets the email text to the given value.
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
        setUsernameText("")
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
        val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()
        if (emailText.value.isEmpty() || !emailRegex.matches(emailText.value)) {
            _errorMessage.value = "Please use a valid email to sign up."
        } else if (usernameText.value.length < 3 || usernameText.value.length > 16) {
            _errorMessage.value = "Username should be 3 to 16 characters."
        } else if (passwordText.value.length < 6) {
            _errorMessage.value = "Password should be at least 6 characters."
        } else {
            val user = userRepository.getUserByEmail(emailText.value)
            if (user != null) {
                _errorMessage.value = "User with the given email already exists!"
            } else {
                userRepository.insertUser(
                    User(
                        username = usernameText.value,
                        password = passwordText.value,
                        email = emailText.value,
                    )
                )
                clearInput()
                return true
            }
        }
        return false
    }
}