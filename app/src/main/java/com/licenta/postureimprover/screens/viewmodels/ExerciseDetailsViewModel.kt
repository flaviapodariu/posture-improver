package com.licenta.postureimprover.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.api.services.WorkoutService
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val workoutService: WorkoutService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val exerciseId: String = savedStateHandle["exerciseId"]!!
    var exercise: Exercise? by mutableStateOf(null)

    init {
        viewModelScope.launch {
            val res = workoutService.getExerciseById(exerciseId.toInt())
            when(res) {
                is Task.Success -> exercise = res.result
                is Task.Failure -> println("todo")
                is Task.Loading -> println("show loading")
            }
        }
    }
}