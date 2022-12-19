package com.licenta.postureimprover.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(): ViewModel() {
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")

    fun onEmailChanged(emailState: String){
        email = emailState
    }

    fun onPasswordChanged(passwordState: String){
        password = passwordState
    }

    fun onConfirmPasswordChanged(confirmPassState: String){
        confirmPassword = confirmPassState
    }

}