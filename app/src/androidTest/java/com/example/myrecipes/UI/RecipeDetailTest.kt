package com.example.myrecipes.UI

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myrecipes.fakes.FakeRecipe
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.UI.RecipeDetail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class RecipeDetailTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private var recipesListViewModel: RecipesListViewModel = mock(RecipesListViewModel::class.java)
//
//    private var savedRecipesViewModel: SavedRecipesViewModel = mock(SavedRecipesViewModel::class.java)
//
//    @Before
//    fun setUp() {
////        `when`(recipesListViewModel.getRecipeById(anyString())).thenReturn(FakeRecipe)
//
//        composeTestRule.setContent {
//            RecipeDetail(
//                modelViewModel = recipesListViewModel,
//                uiState = RecipesListViewModel.UiState(),
//                savedRecipesViewModel = savedRecipesViewModel,
//                recipeId = "",
//                user_id = 0
//            )
//        }
//    }
//
//    @Test
//    fun testErrorMessage() {
//        `when`(recipesListViewModel.getRecipeById(anyString())).thenReturn(null)
//
//        composeTestRule.onNodeWithText("Recipe not found").assertIsDisplayed()
//    }
}