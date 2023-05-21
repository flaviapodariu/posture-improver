package com.licenta.postureimprover.data.api.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UserExercise(
    val userId : Int,
    val exerciseId : Int,
    val reps : Int
)
