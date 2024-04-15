package com.example.myrecipes.view.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myrecipes.R
import com.example.myrecipes.view.navigation.Screen

@Composable
fun Home(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.recipe),
            contentDescription = "Recipe Book",
            modifier = Modifier.size(248.dp).padding(8.dp)
        )

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                navController.navigate(Screen.SIGNUP.route)
            },
            modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(0.8f)
        ) {
            Text(text = stringResource(id = R.string.signup))
        }

        Button(
            onClick = {
                navController.navigate(Screen.LOGIN.route)
            },
            modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(0.8f),
        ) {
            Text(text = stringResource(id = R.string.login))
        }

//        Button(
//            onClick = {
//                navController.navigate(Screen.RECIPE_LIST.route)
//            },
//            modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(0.8f)
//        ) {
//            Text(text = "Recipes List")
//        }
//
//
//        Button(
//            onClick = {
//                navController.navigate(Screen.RECIPE_DETAIL.route)
//            },
//            modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(0.8f)
//        ) {
//            Text(text = "Recipe Item")
//        }

    }
}