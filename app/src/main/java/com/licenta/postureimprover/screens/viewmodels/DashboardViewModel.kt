package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.kalendar.KalendarEvent
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.repositories.CaptureRepository
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.util.HEAD_FORWARD_NORMAL
import com.licenta.postureimprover.util.ROUNDED_SHOULDERS_NORMAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val captureRepository: CaptureRepository,
    private val prefs: SharedPreferences
): ViewModel() {

    var userCaptures: List<CaptureEntity> by mutableStateOf(emptyList())
    var displayEvents: Boolean by mutableStateOf(false)

    var displayGraphic: Boolean by mutableStateOf(false)
    var displayHfGraphic: Boolean by mutableStateOf(false)
    var displayRsGraphic: Boolean by mutableStateOf(false)

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

    fun  displayEvents(date: LocalDate, event: KalendarEvent) {
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
    fun computeImprovement(lastTime: Float, current: Float) : Float {
        return (current - lastTime) * 100 / lastTime
    }

    private fun computeSymptomPercent(correct: Float, actual: Float) : Int {
        return ((correct - actual) * 100 / correct).roundToInt()
    }

    fun eventDescription(capture: CaptureEntity) : String {
        val hf = computeSymptomPercent(HEAD_FORWARD_NORMAL, capture.headForward)
        val rs = computeSymptomPercent(ROUNDED_SHOULDERS_NORMAL, capture.roundedShoulders)

        val desc = "Symptoms were seen in percentages of: "
        val hfText = "\n Head Forward ≈ $hf%"
        val rsText = "\n Rounded Shoulders ≈ $rs%"

        if(hf > 0 && rs > 0) {
            return desc + hfText + rsText
        }
        else if(hf > 0) {
            return desc + hfText
        }
        else if(rs > 0) {
            return desc + rsText
        }
        else return "Keep it up! \nThere were no postural problems in this scan"
    }

    fun asKalendarEvent(capture: CaptureEntity) = KalendarEvent(
        date = capture.date.toKotlinLocalDate(),
        eventName = "Posture Scan",
        eventDescription = eventDescription(capture)
    )
}