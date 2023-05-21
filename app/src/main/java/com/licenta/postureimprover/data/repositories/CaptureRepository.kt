package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.response.asEntity
import com.licenta.postureimprover.data.api.services.CaptureApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.asCaptureReq
import com.licenta.postureimprover.data.util.networkBoundTask
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CaptureRepository @Inject constructor(
    private val api: CaptureApi,
    private val db: PostureDatabase
) {

    private val captureDao = db.capturesDao
    fun getCaptures(token: String) = networkBoundTask(
        query = {
            captureDao.getAllCaptures(USER_ID)
        },
        fetch = {
            api.getUserCaptures(token)
        },
        saveFetchResult = { captures ->
            db.withTransaction {
                captureDao.deleteAllCaptures(USER_ID)
                captureDao.insertCaptures(captures?.data?.map { it.asEntity() }!!)
            }
        },
    )
    suspend fun getLocalCaptures() : List<CaptureEntity> {
        return captureDao.getAllCaptures(USER_ID).first()
    }

    suspend fun saveCaptureLocally(capture: CaptureEntity) {
        captureDao.insertCapture(capture)
    }

    suspend fun sendBulkCaptures(captures: List<CaptureEntity>, token: String) {
        captures.forEach {
            api.insertCapture(it.asCaptureReq(), token)
        }
    }

}