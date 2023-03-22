package com.licenta.postureimprover.data.api.dto.request
import kotlinx.serialization.Serializable
@Serializable
data class LoginReq(
    val email: String,
    val password: String
)
