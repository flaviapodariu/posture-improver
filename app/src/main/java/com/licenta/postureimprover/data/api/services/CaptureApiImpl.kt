package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.ApiRoutes
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import com.licenta.postureimprover.data.util.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class CaptureApiImpl @Inject constructor(
    private val client: HttpClient,
) : CaptureApi {

    override suspend fun getUserCaptures(token: String): Task<List<CaptureRes>>? {
        return try {
            val res: List<CaptureRes> = client.get {
                url(ApiRoutes.DASHBOARD)
                bearerAuth(token)
            }.body()

            Task.Success(res)
        }
        catch(e: Exception){
            Task.Failure(e)
        }
    }

    override suspend fun insertCapture(capture: CaptureReq, token: String): Task<Boolean>? {
        return try {
           val res: Boolean = client.post {
            url(ApiRoutes.DASHBOARD)
            contentType(ContentType.Application.Json)
            setBody(capture)
            bearerAuth(token)
        }.body()

            Task.Success(res)
        }
        catch(e: ClientRequestException) {
            e.printStackTrace()
            Task.Failure(e)
        }

        catch(e: Exception) {
            e.printStackTrace()
            Task.Failure(e)

        }

    }
}