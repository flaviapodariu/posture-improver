package com.licenta.postureimprover.data.repositories

import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import com.licenta.postureimprover.data.util.Task
import kotlinx.coroutines.flow.Flow

interface CaptureRepository {

    fun getAllCaptures(userId: Int) : Flow<List<CaptureRes>>

    suspend fun insertCapture(capture: CaptureReq, token: String) : Task<Boolean>?
}