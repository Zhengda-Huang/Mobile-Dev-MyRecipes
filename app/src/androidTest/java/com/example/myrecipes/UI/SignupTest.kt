package com.example.myrecipes.UI

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myrecipes.modelview.SignupViewModel
import com.example.myrecipes.view.UI.Signup
import com.example.myrecipes.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class SignupTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var navController: NavHostController = Mockito.mock(NavHostController::class.java)

    private var signupViewModel: SignupViewModel = Mockito.mock(SignupViewModel::class.java)

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.setContent {
            Signup(
                viewModel = signupViewModel,
                uiState = SignupViewModel.UiState(),
                navController = navController,
            )
        }
    }

    @Test
    fun testButtons() {
        val signupButton = composeTestRule.onNodeWithText(context.getString(R.string.signup))
        signupButton.assertIsDisplayed()
        signupButton.assertHasClickAction()

        val backButton = composeTestRule.onNodeWithText(context.getString(R.string.back))
        backButton.assertIsDisplayed()
        backButton.assertHasClickAction()
    }

    @Test
    fun testInputLabelsVisible() {
        composeTestRule.onNodeWithText(context.getString(R.string.email)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.username)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.password)).assertIsDisplayed()
    }

    @Test
    fun testTextFiledInteractions() {
        val emailTextField = composeTestRule.onNodeWithText("enter email")
        val usernameTextField = composeTestRule.onNodeWithText("create a username")
        val passwordTextField = composeTestRule.onNodeWithText("create a password")

        emailTextField.assertIsDisplayed()
        emailTextField.assertHasClickAction()

        usernameTextField.assertIsDisplayed()
        usernameTextField.assertHasClickAction()

        passwordTextField.assertIsDisplayed()
        passwordTextField.assertHasClickAction()

        emailTextField.assertIsNotFocused()
        emailTextField.performClick()
        emailTextField.assertIsFocused()

        usernameTextField.assertIsNotFocused()
        usernameTextField.performClick()
        emailTextField.assertIsNotFocused()
        usernameTextField.assertIsFocused()

        passwordTextField.assertIsNotFocused()
        passwordTextField.performClick()
        usernameTextField.assertIsNotFocused()
        passwordTextField.assertIsFocused()
    }
}