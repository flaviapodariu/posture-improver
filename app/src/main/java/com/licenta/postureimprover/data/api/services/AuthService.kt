package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.response.AuthRes
import com.licenta.postureimprover.data.api.dto.request.LoginReq
import com.licenta.postureimprover.data.api.dto.request.RegisterReq
import com.licenta.postureimprover.data.util.Task

interface AuthService {

    suspend fun login(loginReq: LoginReq) : Task<AuthRes>

    suspend fun register(registerUser: RegisterReq) : Task<AuthRes>
}