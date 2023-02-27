package com.licenta.postureimprover.data.api.dto
import kotlinx.serialization.Serializable
@Serializable
data class AuthRes(
    val token: String,
    val nickname: String
)
