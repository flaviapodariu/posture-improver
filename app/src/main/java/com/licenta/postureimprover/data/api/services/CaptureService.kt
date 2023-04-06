package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.response.PostureHistory
import com.licenta.postureimprover.data.util.Task

interface CaptureService {
    suspend fun getUserCaptures(token: String) : Task<PostureHistory>?
    suspend fun insertCapture(capture: CaptureReq, token: String) : Task<Boolean>?

}