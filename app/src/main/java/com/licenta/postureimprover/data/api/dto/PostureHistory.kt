package com.licenta.postureimprover.data.api.dto

import kotlinx.serialization.Serializable
import javax.inject.Named

@Serializable
data class PostureHistory(
    val captures: List<CaptureRes>
)
