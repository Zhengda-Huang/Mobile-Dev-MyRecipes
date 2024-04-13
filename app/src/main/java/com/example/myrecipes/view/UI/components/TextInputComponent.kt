package com.example.myrecipes.view.UI.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

/**
 * simple abstraction for text input components with
 * a label and value change handling
 */
@Composable
fun TextInputComponent(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = label)
        if (isPassword) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        } else {
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true
            )
        }
    }
}