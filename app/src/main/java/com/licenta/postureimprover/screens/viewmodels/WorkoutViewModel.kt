package com.licenta.postureimprover.screens.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.repositories.WorkoutRepository
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    prefs: SharedPreferences
) : ViewModel() {

    val token = prefs.getString("jwt", "no_token") as String
    var exerciseList: List<ExerciseEntity> by mutableStateOf(listOf())

     fun getWorkout() {
        viewModelScope.launch {
            workoutRepository.getWorkout(token).collect {
                when(it) {
                    is Task.Failure -> println("do something ")
                    else -> {
                        exerciseList = it.data!!
                    }
                }
            }
        }
    }
}