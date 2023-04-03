package com.licenta.postureimprover.data.repositories

import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.asNetworkModel
import com.licenta.postureimprover.data.util.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineCaptureRepository(
    private val captureService: CaptureService,
    private val capturesDao: CapturesDao
) : CaptureRepository {

    override fun getAllCaptures(userId: Int): Flow<List<CaptureRes>> {
        return capturesDao.getAllCaptures(userId).map{
            it.map(CaptureEntity::asNetworkModel)
        }
    }

    override suspend fun insertCapture(capture: CaptureReq, token: String): Task<Boolean>? {
        return captureService.insertCapture(capture, token)
    }
}