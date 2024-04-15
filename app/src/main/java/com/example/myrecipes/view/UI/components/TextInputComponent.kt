package com.example.myrecipes.view.UI.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

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
    placeholderText: String? = null
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp),
    ) {
        Text(text = label)
        if (isPassword) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = ({
                    placeholderText?.run {
                        Text(text = this)
                    }
                }),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        } else {
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = ({
                    placeholderText?.run {
                        Text(text = this)
                    }
                }),
            )
        }
    }
}