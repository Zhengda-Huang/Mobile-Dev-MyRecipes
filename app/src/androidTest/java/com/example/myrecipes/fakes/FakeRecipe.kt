package com.example.myrecipes.fakes

import com.example.myrecipes.model.database.Recipes.Recipe

val FakeRecipe = Recipe(
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