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
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
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
                    timeout {
                        requestTimeoutMillis = 2000
                    }
                }.body()
                isLoading = false
            }
            catch (e: SocketTimeoutException) {
                // rely on room db until server is online
                isLoading = false
            }
            catch(e: HttpRequestTimeoutException) {
                isLoading = false
            }
            catch(e: Exception) {
                e.printStackTrace()
            }

        }
    }

}