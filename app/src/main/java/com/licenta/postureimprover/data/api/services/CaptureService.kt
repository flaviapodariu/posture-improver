package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.PostureHistory
import com.licenta.postureimprover.data.api.dto.WorkoutRes
import com.licenta.postureimprover.data.util.AuthResponse
import com.licenta.postureimprover.domain.models.PostureCapture

interface CaptureService {

    suspend fun getUserCaptures(token: String) : AuthResponse<PostureHistory>

    suspend fun sendCapture(capture: PostureCapture) : AuthResponse<WorkoutRes>

}