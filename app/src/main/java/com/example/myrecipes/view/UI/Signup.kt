package com.example.myrecipes.view.UI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myrecipes.R
import com.example.myrecipes.modelview.SignupViewModel
import com.example.myrecipes.view.UI.components.TextInputComponent
import com.example.myrecipes.view.navigation.Screen


@Composable
fun Signup(
    viewModel: SignupViewModel/*= viewModel()*/,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val usernameTextState = viewModel.usernameText.collectAsState()
    val emailTextState = viewModel.emailText.collectAsState()
    val passwordTextState = viewModel.passwordText.collectAsState()
    val errorMessageState = viewModel.errorMessage.collectAsState()

    // displays Toast for error
    if (errorMessageState.value.isNotEmpty()) {
        Toast.makeText(
            context,
            errorMessageState.value,
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
            label = stringResource(id = R.string.username),
            value = usernameTextState.value,
            onValueChange = { viewModel.setUsernameText(it) }
        )

        TextInputComponent(
            label = stringResource(id = R.string.email),
            value = emailTextState.value,
            onValueChange = { viewModel.setEmailText(it) }
        )

        TextInputComponent(
            label = stringResource(id = R.string.password),
            value = passwordTextState.value,
            onValueChange = { viewModel.setPasswordText(it) },
            isPassword = true
        )

        Button(
            onClick = {
                if (viewModel.isValidSignUp()) {
                    navController.navigate(Screen.RECIPE_LIST.name)
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.signup))
        }
    }
}