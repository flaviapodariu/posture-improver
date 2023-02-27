package com.licenta.postureimprover.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.AuthenticationService
import com.licenta.postureimprover.data.api.dto.AuthRes
import com.licenta.postureimprover.data.api.dto.LoginReq
import com.licenta.postureimprover.data.api.dto.RegisterReq
import com.licenta.postureimprover.data.util.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
): ViewModel()
{
    var email: String by mutableStateOf("")
    var nickname: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")

    var authState: AuthResponse<AuthRes>? by mutableStateOf(null)

    fun onEmailChanged(emailState: String) {
        email = emailState
    }

    fun onNicknameChanged(nicknameState: String) {
        nickname = nicknameState
    }

    fun onPasswordChanged(passwordState: String) {
        password = passwordState
    }

    fun onConfirmPasswordChanged(confirmPassState: String) {
        confirmPassword = confirmPassState
    }


    fun login() {
        viewModelScope.launch {
            authenticationService.login(LoginReq(email, password))?.let {
                authState = it
            }
        }
    }

    fun logout() {
        authenticationService.logout()
        authState = null
    }

    fun register() {
        if (password == confirmPassword) {
            viewModelScope.launch {
                authenticationService.register(RegisterReq(email, nickname, password))?.let {
                    authState = it
                }
            }

        }
    }

}