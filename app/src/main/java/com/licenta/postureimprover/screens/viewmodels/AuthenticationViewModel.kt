package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.request.LoginReq
import com.licenta.postureimprover.data.api.dto.request.RegisterReq
import com.licenta.postureimprover.data.api.dto.response.AuthRes
import com.licenta.postureimprover.data.api.services.AuthApi
import com.licenta.postureimprover.data.local.entities.asUserExercise
import com.licenta.postureimprover.data.repositories.CaptureRepository
import com.licenta.postureimprover.data.repositories.ExercisesRepository
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val prefs: SharedPreferences,
    private var captureRepository: CaptureRepository,
    private var exercisesRepository: ExercisesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel()
{
    val alsoMigrateData: String? = savedStateHandle["migration"]

    var email: String by mutableStateOf("")
    var nickname: String by mutableStateOf("")
    var visitorNickname: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")

    var authState: Task<AuthRes>? by mutableStateOf(null)

    var showVisitorDialog: Boolean by mutableStateOf(false)

    fun onEmailChanged(emailState: String) {
        email = emailState
    }

    fun onNicknameChanged(nicknameState: String) {
        nickname = nicknameState
    }

    fun onVisitorNicknameChanged(visitorNicknameState: String) {
        visitorNickname = visitorNicknameState
    }

    fun onPasswordChanged(passwordState: String) {
        password = passwordState
    }

    fun onConfirmPasswordChanged(confirmPassState: String) {
        confirmPassword = confirmPassState
    }

    fun onSuccesfulAuth(userId:Int, token: String, nickname: String) {
        prefs.edit().putString("jwt", token).apply()
        prefs.edit().putInt("userId", userId).apply()
        prefs.edit().putString("nickname", nickname).apply()

        alsoMigrateData?.let {
            if(it == "true") {
                migrateUserData(token)
            }
        }

        USER_ID = userId
    }

    fun login() {
        viewModelScope.launch {
            authApi.login(LoginReq(email, password)).let {
                authState = it
            }
        }
    }

    fun logout() {
        prefs.edit().remove("jwt").apply()
        prefs.edit().remove("userId").apply()
        viewModelScope.launch {
            captureRepository.deleteAllCaptures()
            exercisesRepository.deleteAllExercises()
        }

        authState = null
    }

    fun register() {
        if (password == confirmPassword) {
            viewModelScope.launch {
                authApi.register(RegisterReq(email, nickname, password)).let {
                    authState = it
                }
            }
        }
    }

    fun loadVisitorDialog() {
        showVisitorDialog = true
    }

    fun createVisitorAccount() {
        USER_ID = 0
        prefs.edit().putBoolean("isVisitor", true).apply()
        prefs.edit().putString("nickname", visitorNickname).apply()
        showVisitorDialog = false
    }

    private fun migrateUserData(token: String) {
        viewModelScope.launch {
            try {
                val captures = captureRepository.getLocalCaptures()
                captureRepository.sendBulkCaptures(captures, token)

                val exercises = exercisesRepository.getAllExercisesForCurrentUser()
                    .map{ it.asUserExercise() }
                exercisesRepository.sendBulkExercises(exercises, token)

            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    companion object {
        var USER_ID = 0
    }

}