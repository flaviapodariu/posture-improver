package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.CaptureRes
import com.licenta.postureimprover.data.api.dto.PostureHistory
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val captureService: CaptureService,
    private val prefs: SharedPreferences
): ViewModel() {

    init {
        getUserHistory()
    }
    var userHistoryState: Task<PostureHistory>? by mutableStateOf(null)
    var userCaptures: List<CaptureRes>? by mutableStateOf(null)

    fun onUserCaptureChange(newCaptures: List<CaptureRes>) {
        userCaptures = newCaptures
    }

    private fun getUserHistory() {
        viewModelScope.launch {
           userHistoryState = captureService.getUserCaptures(prefs.getString("jwt", "no_token")!!)
        }
    }
}