package com.example.myrecipes.UI

import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myrecipes.modelview.LoginViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.UI.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class LoginTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var navController: NavHostController = mock(NavHostController::class.java)

    @Before
    fun setUp() {
        composeTestRule.setContent {

        }
    }

}