package com.licenta.postureimprover.data.api.dto.response
import kotlinx.serialization.Serializable
@Serializable
data class AuthRes(
    val token: String,
    val nickname: String
)
