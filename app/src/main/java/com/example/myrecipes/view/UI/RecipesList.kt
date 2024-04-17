package com.example.myrecipes.view.UI

import SaveRecipeButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun RecipesList(
    modelViewModel: RecipesListViewModel,
    uiState: RecipesListViewModel.UiState,
    savedRecipesViewModel: SavedRecipesViewModel,
    navController: NavController,
    user_id: Long
) {
//    val productsState = modelViewModel.recipes.collectAsState()
//    val loadingState = modelViewModel.loading.collectAsState()
//    val errorState = modelViewModel.error.collectAsState()
//    val pageState = modelViewModel.page.collectAsState()
//
//    // Access the values
//    val products = productsState.value
//    val isLoading = loadingState.value
//    val isError = errorState.value
    val products = uiState.recipes
    val isLoading = uiState.loading
    val isError = uiState.error

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when {
            isLoading -> {
                // Display loading indicator
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            isError -> {
                // Display error message
                Text(
                    "An error occurred, please try again",
                    color = Color.Red,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    products.forEach { recipe ->
                        RecipeCard(recipe = recipe, modelView = savedRecipesViewModel, user_id = user_id, recipeListViewModel = modelViewModel, onClickHandler =  {
                            val route =
                                NavigationItem.RecipeDetail.createRoute(recipeId = recipe.idMeal)
                            navController.navigate(route)
                        })
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Button(onClick = {val route =
                            NavigationItem.RecipeList.route
                            navController.navigate(route) }) {
                            Text("Home")
                        }
                        Button(onClick = {val route =
                            NavigationItem.SavedRecipes.route
                            navController.navigate(route) }) {
                            Text("Saved")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun RecipeCard(recipe: Recipe, onClickHandler: () -> Unit, modelView : SavedRecipesViewModel, user_id:Long, recipeListViewModel: RecipesListViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .clickable { onClickHandler() }
    ) {
        Image(
            painter = rememberImagePainter(data = recipe.strMealThumb),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 2.dp, top = 10.dp)
                .align(Alignment.Top)
        ) {
            Text(
                text = recipe.strMeal, style = MaterialTheme.typography.h6
            )
            Text(
                text = "Category: ${recipe.strCategory}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = "Tags: ${recipe.strTags}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 2.dp)
            )
            SaveRecipeButton(savedRecipesViewModel = modelView, recipeId=recipe.idMeal, user_id=user_id, recipesListViewModel = recipeListViewModel)

        }

//        Column(
//            modifier = Modifier
//                .padding(start = 2.dp, top = 10.dp)
//                .align(Alignment.Top)
//        ) {
//            SaveRecipeButton(savedRecipesViewModel = modelView, recipeId=recipe.idMeal, user_id=user_id)
//        }
    }
}

@Preview
@Composable
fun LoadingStatePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.White)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }

}


@Preview(showBackground = true)
@Composable
fun ErrorStatePreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()  // This makes the Box fill the entire available space in the preview
    ) {
        Text(
            "An error occurred, please try again",
            color = Color.Red,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun RecipeCardPreview() {
    val recipe = Recipe(
        idMeal = "12345",
        strMeal = "Sample Recipe Name",
        strDrinkAlternate = null, // Nullable field
        strCategory = "Dessert",
        strArea = "International",
        strInstructions = "Mix all ingredients and bake for 45 minutes at 350 degrees.",
        strMealThumb = "file:///android_res/drawable/your_placeholder_image.png",
        strTags = "Easy, Quick",
        strYoutube = "https://youtube.com/samplevideo",
        strIngredient1 = "Flour",
        strIngredient2 = "Sugar",
        strIngredient3 = "Eggs",
        strIngredient4 = "Butter",
        strIngredient5 = "Baking Soda",
        strIngredient6 = "Salt",
        strIngredient7 = "Vanilla Extract",
        strIngredient8 = "Milk",
        strIngredient9 = "Cocoa Powder",
        strIngredient10 = "Chocolate Chips",
        strMeasure1 = "1 Cup",
        strMeasure2 = "200g",
        strMeasure3 = "3 Large",
        strMeasure4 = "100g",
        strMeasure5 = "1 Tsp",
        strMeasure6 = "1 Tsp",
        strMeasure7 = "2 Tsp",
        strMeasure8 = "200ml",
        strMeasure9 = "50g",
        strMeasure10 = "100g",
        strSource = "https://www.samplewebsite.com/recipes/sample-recipe-name",
        strImageSource = null, // Nullable field
        strCreativeCommonsConfirmed = null, // Nullable field
        dateModified = null // Nullable field
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.carlo),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 2.dp, top = 10.dp)
                    .align(Alignment.Top)
            ) {
                Text(
                    text = recipe.strMeal, style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Category: ${recipe.strCategory}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = "Tags: ${recipe.strTags}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}