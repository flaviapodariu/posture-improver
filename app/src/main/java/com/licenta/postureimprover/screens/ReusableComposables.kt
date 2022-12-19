package com.licenta.postureimprover.screens

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisibleTextInput(
    text: String,
    onTextChanged: (String) -> Unit
) {

    val keys = KeyboardOptions(keyboardType = KeyboardType.Email)
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(text= "Email") },
        placeholder = { Text(text= "Email") },
        keyboardOptions = keys,
        singleLine = true,
        maxLines = 1
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtectedTextInput(
    text: String,
    onTextChanged: (String) -> Unit
) {

    val keys = KeyboardOptions(keyboardType = KeyboardType.Password)
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(text= "Password") },
        placeholder = { Text(text= "Password") },
        keyboardOptions = keys,
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1
    )

}