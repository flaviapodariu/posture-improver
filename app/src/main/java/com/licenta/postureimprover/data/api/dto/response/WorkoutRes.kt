package com.licenta.postureimprover.data.api.dto.response

import com.licenta.postureimprover.data.api.dto.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRes(
    val exercise: Exercise,
    val reps: Int
)
