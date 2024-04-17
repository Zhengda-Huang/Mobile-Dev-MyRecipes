package com.example.myrecipes.UI

import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myrecipes.modelview.LoginViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.UI.Login
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class LoginTest {
    @get:Rule
    val composeTestRule = createComposeRule()

//    @Mock
//    private lateinit var navController: NavHostController 

    @Mock
    private lateinit var savedRecipesViewModel: SavedRecipesViewModel

    @Mock
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        composeTestRule.setContent {
//            Login(
//                navController = navController,
//                savedRecipesViewModel = savedRecipesViewModel,
//                viewModel = loginViewModel
//            )
            Text(text = "hey")
        }
    }

    @Test
    fun testLoginScreen() {
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }
}