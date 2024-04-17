package com.example.myrecipes.UI

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myrecipes.view.UI.Home
import com.example.myrecipes.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class HomeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var navController: NavHostController = mock(NavHostController::class.java)

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.setContent {
            Home(navController = navController)
        }
    }

    @Test
    fun testAppNameVisible() {
        composeTestRule.onNodeWithText(context.getString(R.string.app_name)).assertIsDisplayed()
    }

    @Test
    fun testHomeButtonsVisible() {
        composeTestRule.onNodeWithText(context.getString(R.string.signup)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.login)).assertIsDisplayed()
    }

    @Test
    fun testHomeImageVisible() {
        composeTestRule.onNodeWithContentDescription("Recipe Book").assertIsDisplayed()
    }

    @Test
    fun testHomeButtonsFunctionality() {
        composeTestRule.onNodeWithText(context.getString(R.string.signup)).assertHasClickAction()
        composeTestRule.onNodeWithText(context.getString(R.string.login)).assertHasClickAction()
    }
}