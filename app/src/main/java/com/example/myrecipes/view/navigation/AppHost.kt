package com.example.myrecipes.view.navigation

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.work.WorkManager
import com.example.myrecipes.model.database.User.UserRepository
import com.example.myrecipes.modelview.LoginViewModel
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.modelview.SignupViewModel
import com.example.myrecipes.view.UI.FavoriteList
import com.example.myrecipes.view.UI.Home
import com.example.myrecipes.view.UI.Login
import com.example.myrecipes.view.UI.RecipeDetail
import com.example.myrecipes.view.UI.RecipesList
import com.example.myrecipes.view.UI.Signup


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
        val userRepository = UserRepository(application.applicationContext)
        val recipeListViewModel = RecipesListViewModel(application, workManager)
        val savedRecipesViewModel = SavedRecipesViewModel(application)
        val user_id = savedRecipesViewModel.uiState.value.userId
        val loginViewModel = LoginViewModel(
            application = application,
            userRepository = userRepository
        )
        val signupViewModel = SignupViewModel(
            application = application,
            userRepository = userRepository
        )

        composable(NavigationItem.Home.route) {
            Home(navController = navController)
        }
        composable(NavigationItem.Login.route) {
            Login(
                viewModel = loginViewModel,
                uiState = loginViewModel.uiState.collectAsState().value,
                savedRecipesViewModel = savedRecipesViewModel,
                navController = navController
            )
        }
        composable(NavigationItem.Signup.route) {
            Signup(
                viewModel = signupViewModel,
                uiState = signupViewModel.uiState.collectAsState().value,
                navController = navController
            )
        }
        composable(NavigationItem.RecipeList.route) {
            RecipesList(
                modelViewModel = recipeListViewModel,
                uiState = recipeListViewModel.uiState.collectAsState().value,
                navController = navController,
                savedRecipesViewModel = savedRecipesViewModel,
                user_id = user_id
            )
        }
        composable(NavigationItem.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) {
            val recipeId = it.arguments?.getString("recipeId")
            if (recipeId != null) {
                // There needs to be a valid existing recipeId to access this route
                RecipeDetail(
                    modelViewModel = recipeListViewModel,
                    uiState = recipeListViewModel.uiState.collectAsState().value,
                    recipeId = recipeId,
                    savedRecipesViewModel = savedRecipesViewModel,
                    user_id = user_id
                )
            }
        }
        composable(NavigationItem.SavedRecipes.route){
            FavoriteList(
                modelViewModel= savedRecipesViewModel,
                savedRecipesUiState = savedRecipesViewModel.uiState.collectAsState().value,
                navController= navController,
                recipeListViewModel= recipeListViewModel
            )
        }
    }
}