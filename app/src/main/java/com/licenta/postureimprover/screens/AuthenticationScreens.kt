package com.licenta.postureimprover.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.R
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.screens.components.ProtectedTextInput
import com.licenta.postureimprover.screens.components.VisibleTextInput
import com.licenta.postureimprover.screens.components.VisitorDialog
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel
import com.licenta.postureimprover.theme.authChangeOptionButtonStyle

@Composable
fun LoginScreen(
    goToDashboard: (String) -> Unit = {},
    goToSignUp: () -> Unit = {},
    authViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = authViewModel.authState, key2 = context) {
        authViewModel.authState?.let {
            when(it) {
                is Task.Success -> {
                    authViewModel.onSuccesfulAuth(it.data!!.userId, it.data.token, it.data.nickname)
                    goToDashboard(it.data.nickname)
                }
                is Task.Failure -> {
                    println(it.error!!.message)
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        VisibleTextInput(
            text = authViewModel.email,
            label = "Email",
            onTextChanged = { authViewModel.onEmailChanged(it) }
        )
        ProtectedTextInput(
            text = authViewModel.password,
            label = "Password",
            onTextChanged = { authViewModel.onPasswordChanged(it) }
        )
        Button(
            onClick = { authViewModel.login() },
            content = { Text("Log In") }
        )
        SignUpRedirect(goToSignUp)
        
//     temp solution
        Button(
            onClick = {
                authViewModel.loadVisitorDialog()
            },
            content = { Text("Log in as visitor")}
        )

    }
    
    if(authViewModel.showVisitorDialog) {
        VisitorDialog(
            onClickAction = {
                authViewModel.createVisitorAccount()
                goToDashboard(authViewModel.visitorNickname)
            },
            dialogHeading = "Visitor account warning" ,
            dialogText = stringResource(id = R.string.visitor_warning),
            buttonText = "Save",
            userInput = authViewModel.visitorNickname,
            onInputChanged = { authViewModel.onVisitorNicknameChanged(it) }
        )
    }


}

@Composable
fun SignUpRedirect(
    goToSignUp: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text= "Don't have an account? ")
        ClickableText(
            text = AnnotatedString(text="Sign Up"),
            style= authChangeOptionButtonStyle(),
            onClick = { goToSignUp() }
        )
    }
}


@Composable
fun SignUpScreen(
    goToDashboard: (String) -> Unit = {},
    goToLogin: () -> Unit,
    authViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    
    LaunchedEffect(key1 = authViewModel.authState, key2 = context) {
        authViewModel.authState?.let {
            when(it) {
                is Task.Success -> {
                    authViewModel.onSuccesfulAuth(it.data!!.userId, it.data.token, it.data.nickname)
                    goToDashboard(it.data.nickname)
                }
                is Task.Failure ->
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                else -> Unit
            }

        }
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        VisibleTextInput(
            text = authViewModel.email,
            label = "Email",
            onTextChanged = { authViewModel.onEmailChanged(it) }
        )

        VisibleTextInput(
            text = authViewModel.nickname,
            label = "Nickname",
            onTextChanged = { authViewModel.onNicknameChanged(it) }
        )

        ProtectedTextInput(
            text = authViewModel.password,
            label = "Password",
            onTextChanged = { authViewModel.onPasswordChanged(it) }
        )

        ProtectedTextInput(
            text = authViewModel.confirmPassword,
            label = "Confirm password",
            onTextChanged = { authViewModel.onConfirmPasswordChanged(it) }
        )
        Button(
            onClick = { authViewModel.register() },
            content = { Text("Sign Up") }
        )
        LogInRedirect(goToLogin)
    }
}

@Composable
fun LogInRedirect(
    goToLogin: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text= "Already have an account? ")
        ClickableText(
            text = AnnotatedString(text="Log In"),
            style= authChangeOptionButtonStyle(),
            onClick = { goToLogin() }
        )
    }

}