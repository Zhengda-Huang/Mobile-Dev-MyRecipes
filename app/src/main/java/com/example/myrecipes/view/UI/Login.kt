package com.example.myrecipes.view.UI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myrecipes.R
import com.example.myrecipes.modelview.LoginViewModel
import com.example.myrecipes.modelview.SavedRecipesViewModel
import com.example.myrecipes.view.UI.components.TextInputComponent
import com.example.myrecipes.view.navigation.Screen
import kotlinx.coroutines.launch

/**
 * login screen
 */
@Composable
fun Login(
    viewModel: LoginViewModel/*= viewModel()*/,
    uiState: LoginViewModel.UiState,
    savedRecipesViewModel : SavedRecipesViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current
//    val user_id = savedRecipesViewModel.user_id.collectAsState()

    // displays Toast for error
    if (uiState.errorMessage.isNotEmpty()) {
        Toast.makeText(
            context,
            uiState.errorMessage,
            Toast.LENGTH_LONG
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputComponent(
            label = stringResource(id = R.string.email),
            value = uiState.emailText,
            onValueChange = { viewModel.setEmailText(it) },
            placeholderText = "enter email"
        )

        TextInputComponent(
            label = stringResource(id = R.string.password),
            value = uiState.passwordText,
            onValueChange = { viewModel.setPasswordText(it) },
            isPassword = true,
            placeholderText = "enter password",
        )

        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                coroutineScope.launch {
                    if (viewModel.isValidCredentials()) {
                        val user_id = viewModel.getUserId()
                        savedRecipesViewModel.updateUserId(user_id)
                        navController.navigate(Screen.RECIPE_LIST.route)
                    }
                }
            },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(0.8f)
        ) {
            Text(text = stringResource(id = R.string.login))
        }

        Button(
            onClick = {
                viewModel.clearAll()
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(0.8f)
        ) {
            Text(text = stringResource(id = R.string.back))
        }
    }
}
