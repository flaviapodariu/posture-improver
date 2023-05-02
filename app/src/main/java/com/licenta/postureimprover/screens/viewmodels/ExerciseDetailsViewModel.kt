package com.licenta.postureimprover.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.repositories.ExercisesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: String = savedStateHandle["exerciseId"]!!
    var exercise: ExerciseEntity? by mutableStateOf(null)

    fun getExerciseById() {
        viewModelScope.launch {
            exercisesRepository.getExerciseById(exerciseId.toInt()).collect {
                exercise = it
            }
        }
    }

    fun displayTargetedMuscles(muscles: String) : String {
        return "◦ " + muscles.trim().replace(",", "◦") + " ◦"
    }

}