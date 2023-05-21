package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.services.CaptureApi
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.repositories.CaptureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private var prefs: SharedPreferences
) : ViewModel() {

    var captures: List<CaptureEntity> by mutableStateOf(emptyList())

    var newUserId: Int = 0
}