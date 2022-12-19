package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.licenta.postureimprover.db.login
import com.licenta.postureimprover.db.register
import com.licenta.postureimprover.ui.styles.authChangeOptionButtonStyle
import com.licenta.postureimprover.navigation.Routes
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
@Composable
fun LoginComponent(
    navController: NavHostController,
    authViewModel: AuthenticationViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        VisibleTextInput(
            text = authViewModel.email,
            onTextChanged = { authViewModel.onEmailChanged(it) }
        )
        ProtectedTextInput(
            text = authViewModel.password,
            onTextChanged = { authViewModel.onPasswordChanged(it) }
        )
        Button(
            onClick = { runBlocking {
                launch{
                    login(authViewModel.email, authViewModel.password, navController)
                }
            } },
            content = { Text("Log In") }
        )
        SignUpRedirect(navController)
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
fun SignUpComponent(
    navController: NavHostController,
    authViewModel: AuthenticationViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        VisibleTextInput(
            text = authViewModel.email,
            onTextChanged = { authViewModel.onEmailChanged(it) }
        )
        ProtectedTextInput(
            text = authViewModel.password,
            onTextChanged = { authViewModel.onPasswordChanged(it) }
        )

        ProtectedTextInput(
            text = authViewModel.confirmPassword,
            onTextChanged = { authViewModel.onConfirmPasswordChanged(it) }
        )
        Button(
            onClick = { runBlocking {
                launch{
                    register(authViewModel.email, authViewModel.password, navController)
                }
            } },
            content = { Text("Sign Up") }
        )
        LogInRedirect(navController)
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

class TextFieldState {
    var text: String by mutableStateOf("")
}