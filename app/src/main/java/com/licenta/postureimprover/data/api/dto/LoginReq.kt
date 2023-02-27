package com.licenta.postureimprover.data.api.dto
import kotlinx.serialization.Serializable
@Serializable
data class LoginReq(
    val email: String,
    val password: String
)
