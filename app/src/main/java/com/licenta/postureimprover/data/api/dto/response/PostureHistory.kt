package com.licenta.postureimprover.data.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class PostureHistory(
    val captures: List<CaptureRes>
)
