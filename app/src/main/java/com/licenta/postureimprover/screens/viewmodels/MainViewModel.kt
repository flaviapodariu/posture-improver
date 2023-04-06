package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.ApiRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val client: HttpClient,
    private val prefs: SharedPreferences,

): ViewModel() {

    var darkTheme: Boolean by mutableStateOf(false)

    private val token = prefs.getString("jwt", "no_token") as String

    var nickname: String by mutableStateOf("")

    var isLoading: Boolean by mutableStateOf(true)

    fun whatUser() {
        viewModelScope.launch {
            try {
                nickname = client.get {
                    url(ApiRoutes.BASE_URL)
                    bearerAuth(token)
                    timeout {
                        requestTimeoutMillis = 2000
                    }
                }.body()
                isLoading = false
            } catch (e: Exception) {
                nickname = prefs.getString("nickname", "")!!
                isLoading = false
                e.printStackTrace()
            }
        }

    }
}