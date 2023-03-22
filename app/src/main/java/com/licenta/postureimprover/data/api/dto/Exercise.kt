package com.licenta.postureimprover.data.api.dto

import kotlinx.serialization.Serializable
@Serializable
data class Exercise(
    val name: String,
    val description: String,
//    val treats: BackProblem,
    val reps: Int
)
