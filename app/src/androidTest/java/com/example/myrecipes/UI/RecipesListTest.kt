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
import com.example.myrecipes.view.UI.RecipesList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class RecipesListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var recipesListViewModel: RecipesListViewModel =
        Mockito.mock(RecipesListViewModel::class.java)

    private var savedRecipesViewModel: SavedRecipesViewModel =
        Mockito.mock(SavedRecipesViewModel::class.java)

    private var navController: NavHostController = Mockito.mock(NavHostController::class.java)


    @Before
    fun setUp() {
        composeTestRule.setContent {
            RecipesList(
                modelViewModel = recipesListViewModel,
                uiState = RecipesListViewModel.UiState(
                    loading = false,
                    error = false,
                    recipes = listOf(
                        FakeRecipe, FakeRecipe, FakeRecipe
                    )
                ),
                savedRecipesViewModel = savedRecipesViewModel,
                navController = navController,
                user_id = 0
            )
        }
    }

    @Test
    fun testButtons() {
        val homeButton = composeTestRule.onNodeWithText("Home")
        val savedButton = composeTestRule.onNodeWithText("Saved")

        homeButton.assertIsDisplayed()
        homeButton.assertHasClickAction()

        savedButton.assertIsDisplayed()
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