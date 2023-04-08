package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.response.asEntity
import com.licenta.postureimprover.data.api.services.CaptureApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.util.networkBoundTask
import javax.inject.Inject

class CaptureRepository @Inject constructor(
    private val api: CaptureApi,
    private val db: PostureDatabase
) {

    private val captureDao = db.capturesDao
    fun getCaptures(token: String) = networkBoundTask(
        query = {
            captureDao.getAllCaptures()
        },
        fetch = {
            api.getUserCaptures(token)
        },
        saveFetchResult = { captures ->
            db.withTransaction {
                captureDao.deleteAllCaptures()
                captureDao.insertCaptures(captures?.data?.map { it.asEntity() }!!)
            }
        }
    )

}