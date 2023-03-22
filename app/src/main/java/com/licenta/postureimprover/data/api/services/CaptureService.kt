package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.response.PostureHistory
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.data.models.Capture

interface CaptureService {

    suspend fun getUserCaptures(token: String) : Task<PostureHistory>?

    suspend fun sendCapture(capture: Capture) : Task<WorkoutRes>?

}