package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.PostureHistory
import com.licenta.postureimprover.data.api.dto.WorkoutRes
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.data.models.PostureCapture

interface CaptureService {

    suspend fun getUserCaptures(token: String) : Task<PostureHistory>?

    suspend fun sendCapture(capture: PostureCapture) : Task<WorkoutRes>?

}