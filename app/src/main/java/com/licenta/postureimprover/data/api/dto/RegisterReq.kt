package com.licenta.postureimprover.data.api.dto
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReq(
     val email: String,
     val nickname: String,
     val password: String
)
