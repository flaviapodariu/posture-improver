package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import com.licenta.postureimprover.data.util.Task

interface CaptureApi {
    suspend fun getUserCaptures(token: String) : Task<List<CaptureRes>>?
    suspend fun insertCapture(capture: CaptureReq, token: String) : Task<Boolean>?

}