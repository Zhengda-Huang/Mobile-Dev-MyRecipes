package com.example.myrecipes.modelview

import android.app.Application
import com.example.myrecipes.model.database.User.User
import com.example.myrecipes.model.database.User.UserRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: LoginViewModel

    private lateinit var user: User

    private lateinit var uiState: LoginViewModel.UiState

    @Before
    fun setUp() {
        viewModel = LoginViewModel(application, userRepository)
        user = User(
            username = "username", password = "password", email = "example@gmail.com"
        )
        uiState = viewModel.uiState.value
    }

    @Test
    fun `initial state`() {
        assertEquals("", viewModel.uiState.value.emailText)
        assertEquals("", viewModel.uiState.value.passwordText)
        assertEquals("", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test setEmail function`() {
        assertEquals("", uiState.emailText)
        viewModel.setEmailText("example@gmail.com")
        assertEquals("example@gmail.com", viewModel.uiState.value.emailText)
    }

    @Test
    fun `test setPassword function`() {
        assertEquals("", viewModel.uiState.value.passwordText)
        viewModel.setPasswordText("password")
        assertEquals("password", viewModel.uiState.value.passwordText)
    }

    @Test
    fun `test isValidCredentials but blank email`() = runTest {
        viewModel.setEmailText("")
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("Please enter your email correctly.", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test isValidCredentials but invalid email`() = runTest {
        viewModel.setEmailText("email")
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("Please enter your email correctly.", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test isValidCredentials but blank password`() = runTest {
        viewModel.setEmailText("example@gmail.com")
        viewModel.setPasswordText("")
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("Please enter your password.", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test isValidCredentials but user does not exist`() = runTest {
        `when`(userRepository.getUserByEmail(Mockito.anyString())).thenReturn(null)
        viewModel.setEmailText("example@gmail.com")
        viewModel.setPasswordText("password")
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("It seems like an account with this email does not exist.", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test isValidCredentials but user enters wrong password`() = runTest {
        `when`(userRepository.getUserByEmail(Mockito.anyString())).thenReturn(user)
        viewModel.setEmailText("example@gmail.com")
        viewModel.setPasswordText("not password")
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("Incorrect password, try again.", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test isValidCredentials returns true`() = runTest {
        `when`(userRepository.getUserByEmail(Mockito.anyString())).thenReturn(user)
        viewModel.setEmailText("example@gmail.com")
        viewModel.setPasswordText("password")

        assertEquals(true, viewModel.isValidCredentials())
        assertEquals("", viewModel.uiState.value.emailText)
        assertEquals("", viewModel.uiState.value.passwordText)
    }

    @Test
    fun `test error message clears after interaction`() = runTest {
        assertEquals(false, viewModel.isValidCredentials())
        assertEquals("Please enter your email correctly.", viewModel.uiState.value.errorMessage)

        viewModel.setEmailText("example@gmail.com")
        assertEquals("", viewModel.uiState.value.errorMessage)
    }

    @Test
    fun `test clearAll resets all text`() = runTest {
        viewModel.setEmailText("a")
        viewModel.setPasswordText("password")
        viewModel.isValidCredentials()

        assertEquals("a", viewModel.uiState.value.emailText)
        assertEquals("password", viewModel.uiState.value.passwordText)
        assertEquals("Please enter your email correctly.", viewModel.uiState.value.errorMessage)

        viewModel.clearAll()
        assertEquals("", viewModel.uiState.value.emailText)
        assertEquals("", viewModel.uiState.value.passwordText)
        assertEquals("", viewModel.uiState.value.errorMessage)
    }
}