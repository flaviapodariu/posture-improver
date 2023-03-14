package com.licenta.postureimprover.domain.models
import kotlinx.serialization.Serializable

@Serializable
data class PostureCapture(
    val headForward: Float,
    val lordosis: Float,
    val roundedShoulders: Float
)
