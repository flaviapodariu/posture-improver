package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.AuthRes
import com.licenta.postureimprover.data.api.dto.LoginReq
import com.licenta.postureimprover.data.api.dto.RegisterReq
import com.licenta.postureimprover.data.api.services.AuthService
import com.licenta.postureimprover.data.util.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authService: AuthService,
    private val prefs: SharedPreferences
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

    fun onSuccesfulAuth(token: String, nickname: String) {
        prefs.edit().putString("jwt", token).apply()
        prefs.edit().putString("nickname", nickname).apply()


    }

    fun login() {
        viewModelScope.launch {
            authService.login(LoginReq(email, password)).let {
                authState = it
            }
        }
    }

    fun logout() {
        prefs.edit().remove("jwt").apply()
        authState = null
    }

    fun register() {
        if (password == confirmPassword) {
            viewModelScope.launch {
                authService.register(RegisterReq(email, nickname, password)).let {
                    authState = it
                }
            }

        }
    }

}