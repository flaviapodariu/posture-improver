package com.licenta.postureimprover.data.models
import kotlinx.serialization.Serializable

@Serializable
data class Capture(
    val headForward: Float,
    val lordosis: Float,
    val roundedShoulders: Float
)
