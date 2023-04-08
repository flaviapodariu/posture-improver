package com.licenta.postureimprover.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.api.services.WorkoutApi
import com.licenta.postureimprover.data.util.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val workoutApi: WorkoutApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: String = savedStateHandle["exerciseId"]!!
    var exercise: Exercise? by mutableStateOf(null)

    init {
        viewModelScope.launch {
            val res = workoutApi.getExerciseById(exerciseId.toInt())
            when(res) {
                is Task.Success -> exercise = res.data
                is Task.Failure -> println("todo")
                is Task.Loading -> println("show loading")
            }
        }
    }
}