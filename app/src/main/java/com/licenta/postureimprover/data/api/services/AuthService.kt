package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.AuthRes
import com.licenta.postureimprover.data.api.dto.LoginReq
import com.licenta.postureimprover.data.api.dto.RegisterReq
import com.licenta.postureimprover.data.util.Task

interface AuthService {

    suspend fun login(loginReq: LoginReq) : Task<AuthRes>

    suspend fun register(registerUser: RegisterReq) : Task<AuthRes>
}