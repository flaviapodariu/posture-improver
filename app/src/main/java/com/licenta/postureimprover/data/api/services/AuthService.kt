package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.AuthRes
import com.licenta.postureimprover.data.api.dto.LoginReq
import com.licenta.postureimprover.data.api.dto.RegisterReq
import com.licenta.postureimprover.data.util.AuthResponse

interface AuthService {

    suspend fun login(loginReq: LoginReq) : AuthResponse<AuthRes>

    suspend fun register(registerUser: RegisterReq) : AuthResponse<AuthRes>
}