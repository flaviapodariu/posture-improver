package com.licenta.postureimprover.screens.viewmodels

import android.app.Application
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.ApiRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val client: HttpClient,
    private val prefs: SharedPreferences,

): ViewModel() {

    private val token = prefs.getString("jwt", "no_token")!!


    var nickname: String by mutableStateOf("")

    var isLoading: Boolean by mutableStateOf(true)

    init {
        viewModelScope.launch {
            try {
                nickname = client.get {
                    url(ApiRoutes.BASE_URL)
                    bearerAuth(token)
                }.body()
                isLoading = false
            }
            catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}