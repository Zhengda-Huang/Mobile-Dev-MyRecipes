package com.example.myrecipes.view.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myrecipes.modelview.RecipesListViewModel
import java.util.logging.Logger
import com.example.myrecipes.model.database.Recipes.Recipe
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun RecipeDetail(modelViewModel: RecipesListViewModel, recipeId: String) {
    val recipes = modelViewModel.recipes.collectAsState()
    val logger = Logger.getLogger("MyLogger")
    val recipe = recipes.value
    val currentRecipe = modelViewModel.getRecipeById(recipeId)
    val scrollState = rememberScrollState()

    if (currentRecipe == null) {
        // Display an error message if no currentRecipe is found
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Recipe not found", style = MaterialTheme.typography.h6, color = Color.Red)
        }
        return
    }

    // Continue with the normal ui for showing recipe details.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = Color.White)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(color = Color.White)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentRecipe.strMeal,
                    fontSize = 34.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                painter = rememberImagePainter(data = currentRecipe.strMealThumb),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .size(400.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )
            }

            RecipeIngredients(currentRecipe)
            Spacer(modifier = Modifier.height(16.dp))
            RecipeDirections(currentRecipe)
            Spacer(modifier = Modifier.height(16.dp))
            logger.info(currentRecipe.strYoutube)
            YoutubeScreen(videoId = currentRecipe.strYoutube)
        }
    }
}

@Composable
fun RecipeIngredients(recipe: Recipe) {
    val ingredientMap = mutableMapOf<String, String>()
    for (i in 1..20) {
        val ingredient = recipe.getIngredient(i)
        val measure = recipe.getMeasure(i)
        if (ingredient != null && measure != null && ingredient.isNotEmpty() && measure.isNotEmpty()) {
            ingredientMap[ingredient] = measure
        }
    }
    Text(text = "Ingredients",
        fontSize = 32.sp)
    Spacer(modifier = Modifier.height(16.dp))
    ingredientMap.forEach { (ingredient, measure) ->
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = ingredient,
                fontSize = 18.sp)
            Text(text = measure,
                fontSize = 18.sp)
        }
    }
}

@Composable
fun RecipeDirections(recipe: Recipe) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Directions",
                fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(text = recipe.strInstructions,
                    fontSize = 18.sp)
        }
    }
}

@Composable
fun YoutubeScreen(
    videoId: String,
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}



