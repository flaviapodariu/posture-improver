package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.api.services.WorkoutService
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutService: WorkoutService,
    private val prefs: SharedPreferences
) : ViewModel() {

    val token = prefs.getString("jwt", "no_token") as String
    var exerciseList: List<WorkoutRes> by mutableStateOf(listOf())

     fun getWorkout() {
        viewModelScope.launch {
            when(val workoutTask = workoutService.getWorkoutForUser(token)) {
                is Task.Success -> exerciseList = workoutTask.result
                else -> Unit
            }
        }
    }
}