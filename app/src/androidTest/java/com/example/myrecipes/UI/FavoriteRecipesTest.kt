package com.example.myrecipes.UI

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myrecipes.fakes.FakeRecipe
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.UI.FavoriteList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class FavoriteRecipesTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var navController: NavHostController = mock(NavHostController::class.java)

    private var recipeListViewModel: RecipesListViewModel = mock(RecipesListViewModel::class.java)

    private var savedRecipesViewModel: SavedRecipesViewModel = mock(SavedRecipesViewModel::class.java)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            FavoriteList(
                modelViewModel = savedRecipesViewModel,
                savedRecipesUiState = SavedRecipesViewModel.UiState(
                    recipes = listOf(
                        FakeRecipe, FakeRecipe, FakeRecipe
                    )
                ),
                navController = navController,
                recipeListViewModel = recipeListViewModel
            )
        }
    }

    @Test
    fun testButtons() {
        val homeButton = composeTestRule.onNodeWithText("Home")
        val savedButton = composeTestRule.onNodeWithText("Saved")

        homeButton.assertIsDisplayed()
        savedButton.assertIsDisplayed()

        homeButton.assertHasClickAction()
        savedButton.assertHasClickAction()
    }

    @Test
    fun testRecipeCards() {
        val recipeList = composeTestRule.onAllNodesWithText(FakeRecipe.strMeal)
        recipeList[0].assertIsDisplayed()
        recipeList[0].assertHasClickAction()
        recipeList[1].assertIsDisplayed()
        recipeList[1].assertHasClickAction()
        recipeList[2].assertIsDisplayed()
        recipeList[2].assertHasClickAction()
        recipeList[3].assertDoesNotExist()
    }
}