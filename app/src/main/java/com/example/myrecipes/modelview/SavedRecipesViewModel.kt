package com.example.myrecipes.modelview

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkRequest
import com.example.myrecipes.model.database.Recipes.Recipe
import com.example.myrecipes.model.database.Recipes.RecipesRepository
import com.example.myrecipes.model.database.UserSavedRecipes.UserSavedRecipes
import com.example.myrecipes.model.database.UserSavedRecipes.UserSavedRecipesRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.Serializable
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import kotlin.math.log

open class SavedRecipesViewModel(application: Application) {
    private val logger = Logger.getLogger("MyLogger")
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _user_id = MutableStateFlow<Long>(0)
    val user_id : StateFlow<Long> = _user_id

    private var repository: UserSavedRecipesRepository

    init {
        repository = UserSavedRecipesRepository(application.applicationContext)
    }

    fun updateUserId(userId: Long) {
        _user_id.value = userId
    }
    suspend fun addSavedRecipes(user_id: Long, recipe_id: String){
        val recipe = UserSavedRecipes(user_id, recipe_id)
        repository.addSavedRecipes(recipe)

    }

    fun removeRecipe(recipe: Recipe?) {
        val updatedRecipes = _recipes.value.toMutableList()
        if (recipe !== null) {
            updatedRecipes.remove(recipe)
            _recipes.value = updatedRecipes
        }
    }

    fun addRecipe(recipe: Recipe?) {
        val updatedRecipes = _recipes.value.toMutableList()
        if (recipe !== null) {
            updatedRecipes.add(recipe)
            _recipes.value = updatedRecipes
        }
    }

    suspend fun removeSavedRecipes(user_id: Long, recipe_id: String){
        val recipe = UserSavedRecipes(user_id, recipe_id)
        repository.removeSavedRecipes(recipe)
    }

    suspend fun getListRecipeIdFromUserId(user_id: Long): List<String>?{
        return repository.getRecipeListByUserId(user_id)
    }

    suspend fun getSavedRecipe(user_id: Long, recipe_id: String): Boolean{
        val recipe = UserSavedRecipes(user_id, recipe_id)
        var isSaved = false
        runBlocking { // Use runBlocking to block the current thread until coroutine completes
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    isSaved = repository.getSavedRecipe(recipe) >= 1
                    logger.info(repository.getSavedRecipe(recipe).toString())
                } catch (e: Exception) {
                    logger.warning(e.toString())
                }
            }.join() // Wait for the coroutine to complete
        }
        return isSaved
    }
}