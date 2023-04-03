package com.licenta.postureimprover.data.api.dto.request
import kotlinx.serialization.Serializable

@Serializable
data class CaptureReq(
    val headForward: Float,
    val lordosis: Float,
    val roundedShoulders: Float
)
