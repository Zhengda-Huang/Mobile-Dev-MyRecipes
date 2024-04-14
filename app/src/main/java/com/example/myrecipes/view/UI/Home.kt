package com.example.myrecipes.view.UI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myrecipes.modelview.RecipesListViewModel
import com.example.myrecipes.view.navigation.Screen

@Composable
fun Home(modelViewModel: RecipesListViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                navController.navigate(Screen.SIGNUP.route)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "SignUp")
        }

        Button(
            onClick = {
                navController.navigate(Screen.LOGIN.route)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Login")
        }

        Button(
            onClick = {
                navController.navigate(Screen.RECIPE_LIST.route)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Recipes List")
        }


        Button(
            onClick = {
                navController.navigate(Screen.RECIPE_DETAIL.route)
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Recipe Item")
        }

    }
}