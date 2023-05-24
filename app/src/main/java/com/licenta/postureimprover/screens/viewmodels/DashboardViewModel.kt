package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.repositories.CaptureRepository
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val captureRepository: CaptureRepository,
    private val prefs: SharedPreferences
): ViewModel() {

    var userCaptures: List<CaptureEntity> by mutableStateOf(emptyList())
    var displayEvents: Boolean by mutableStateOf(false)

    var dateClicked: LocalDate? by mutableStateOf(java.time.LocalDate.now().toKotlinLocalDate())
    var dayEventDetails: KalendarEvent? by mutableStateOf(null)

    fun getUserHistory() {
        viewModelScope.launch {
            captureRepository.getCaptures(prefs.getString("jwt", "no_token")!!).collect {
                when(it) {
                    is Task.Failure -> {
                        println(it.error)
                    }
                    else -> {
                        userCaptures = it.data!!
                        println("captures:\n $userCaptures")
                    }
                }
            }
        }
    }

    fun displayEvents(date: LocalDate, event: KalendarEvent) {
        if(dateClicked == date)
            displayEvents = !displayEvents
        else{
            displayEvents = true
        }
        if(displayEvents) {
            dateClicked = date
            dayEventDetails = event
        }
    }

}