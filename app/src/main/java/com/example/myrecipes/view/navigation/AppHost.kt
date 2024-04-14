package com.example.myrecipes.view.navigation

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.work.WorkManager
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.view.UI.Home
import com.example.myrecipes.view.UI.RecipeDetail
import com.example.myrecipes.view.UI.RecipesList


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context,
    application: Application,
    workManager: WorkManager
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        val recipeListViewModel = RecipesListViewModel(application, workManager)

        composable(NavigationItem.Home.route) {
            Home(modelViewModel = recipeListViewModel, navController = navController)
        }
        composable(NavigationItem.Login.route) {

        }
        composable(NavigationItem.Signup.route) {
        }
        composable(NavigationItem.RecipeList.route) {
            RecipesList(modelViewModel = recipeListViewModel, navController = navController)
        }
        composable(NavigationItem.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) {
            val recipeId = it.arguments?.getString("recipeId")
            if (recipeId != null) {
                // There needs to be a valid existing recipeId to access this route
                RecipeDetail(modelViewModel = recipeListViewModel, recipeId = recipeId)
            }
        }
    }
}