package com.example.myrecipes.view.navigation

enum class Screen(val route: String) {
    HOME_SCREEN("homeScreen"),
    LOGIN("login"),
    SIGNUP("signup"),
    RECIPE_LIST("recipeList"),
    SAVED_RECIPE_LIST("savedRecipes"),
    RECIPE_DETAIL("recipeDetail/{recipeId}")
}
sealed class NavigationItem(val route: String) {

    object Home : NavigationItem(Screen.HOME_SCREEN.route)
    object Login : NavigationItem(Screen.LOGIN.route)
    object Signup : NavigationItem(Screen.SIGNUP.route)
    object RecipeList : NavigationItem(Screen.RECIPE_LIST.route)

    object SavedRecipes : NavigationItem(Screen.SAVED_RECIPE_LIST.route)

    object RecipeDetail : NavigationItem(Screen.RECIPE_DETAIL.route) {
        fun createRoute(recipeId: String) = "recipeDetail/$recipeId"
    }

}