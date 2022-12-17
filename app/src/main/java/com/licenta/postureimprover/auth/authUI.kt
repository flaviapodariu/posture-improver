package com.licenta.postureimprover.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.licenta.postureimprover.db.login
import com.licenta.postureimprover.db.register
import com.licenta.postureimprover.util.Routes
import com.licenta.postureimprover.ui.styles.authChangeOptionButtonStyle
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun LoginComponent(navController: NavHostController) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val emailState = remember { TextFieldState() }
            val passwordState = remember { TextFieldState() }

            GeneralAuthInput(emailState, passwordState)
            Button(
                onClick = { runBlocking {
                    launch{
                        login(emailState.text, passwordState.text, navController)
                    }
                } },
                content = { Text("Log In") }
            )
            SignUpRedirect(navController)
        }
    }


@Composable
fun SignUpComponent(navController: NavHostController) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val emailState = remember { TextFieldState() }
            val passwordState = remember { TextFieldState() }

            GeneralAuthInput(emailState, passwordState)
            Button(
                onClick = { runBlocking {
                    launch{
                        register(emailState.text, passwordState.text, navController)
                    }
                } },
                content = { Text("Sign Up") }
            )
            LogInRedirect(navController)
        }
    }


@Composable
fun SignUpRedirect(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text= "Don't have an account?")
        ClickableText(
            text = AnnotatedString(text="Sign Up"),
            style= authChangeOptionButtonStyle(),
            onClick = {navController.navigate(Routes.SignUp.route)}
        )
    }
}

@Composable
fun LogInRedirect(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text= "Already have an account?")
        ClickableText(
            text = AnnotatedString(text="Log In"),
            style= authChangeOptionButtonStyle(),
            onClick = {navController.navigate(Routes.Login.route)}
        )
    }

}

@Composable
fun GeneralAuthInput(emailState: TextFieldState, passwordState: TextFieldState) {
    TextInput("Email", emailState)
    TextInput("Password", passwordState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(label: String, valueState : TextFieldState = remember { TextFieldState() }) {
    val visible = (label != "Password")
    val keys = if (visible) KeyboardOptions(keyboardType = KeyboardType.Email)
                else KeyboardOptions(keyboardType = KeyboardType.Password)

    OutlinedTextField(
        value = valueState.text,
        onValueChange = { valueState.text = it },
        label = { Text(text= label) },
        placeholder = { Text(text= label) },
        visualTransformation = if (visible) VisualTransformation.None
                               else PasswordVisualTransformation(),
        keyboardOptions = keys,
        singleLine = true,
        maxLines = 1
    )

}


class TextFieldState(){
    var text: String by mutableStateOf("")
}