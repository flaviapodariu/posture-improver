package com.licenta.postureimprover.data.api.dto

import kotlinx.serialization.Serializable
@Serializable
data class Exercise(
    val id: Int,
    var name: String,
    var description: String,
    var targetedMuscles : String,
    var imageUrl: String
)
