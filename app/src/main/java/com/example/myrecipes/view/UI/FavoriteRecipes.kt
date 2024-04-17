package com.example.myrecipes.view.UI

import SaveRecipeButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myrecipes.R
import com.example.myrecipes.model.database.Recipes.Recipe
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.navigation.NavigationItem

@Composable
fun FavoriteList(
    modelViewModel: SavedRecipesViewModel,
    savedRecipesUiState: SavedRecipesViewModel.UiState,
    navController: NavController,
    recipeListViewModel: RecipesListViewModel
) {
    // Access the values
    val products = savedRecipesUiState.recipes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize() // Fill the entire available vertical space
        ) {
            products.forEach { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modelView = modelViewModel,
                    user_id = savedRecipesUiState.userId,
                    recipeListViewModel =recipeListViewModel ,
                    onClickHandler = {
                        val route =
                            NavigationItem.RecipeDetail.createRoute(recipeId = recipe.idMeal)
                        navController.navigate(route)
                    }
                )
            }

            // Row of buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Add vertical padding
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        val route = NavigationItem.RecipeList.route
                        navController.navigate(route)
                    }
                ) {
                    Text("Home")
                }
                Spacer(modifier = Modifier.width(16.dp)) // Add space between buttons
                Button(
                    onClick = {
                        val route = NavigationItem.SavedRecipes.route
                        navController.navigate(route)
                    }
                ) {
                    Text("Saved")
                }
            }
        }
    }
}


