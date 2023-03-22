package com.licenta.postureimprover.data.api.dto.response

import com.licenta.postureimprover.data.api.dto.Exercise

@kotlinx.serialization.Serializable
data class WorkoutRes(
    val workout: List<Exercise>
)
