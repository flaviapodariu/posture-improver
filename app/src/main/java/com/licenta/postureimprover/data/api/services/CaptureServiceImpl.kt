package com.licenta.postureimprover.data.api.services

import android.content.SharedPreferences
import com.licenta.postureimprover.data.api.ApiRoutes
import com.licenta.postureimprover.data.api.dto.PostureHistory
import com.licenta.postureimprover.data.api.dto.WorkoutRes
import com.licenta.postureimprover.data.util.AuthResponse
import com.licenta.postureimprover.domain.models.PostureCapture
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class CaptureServiceImpl @Inject constructor(
    private val client: HttpClient,
) : CaptureService {

    override suspend fun getUserCaptures(token: String): AuthResponse<PostureHistory> {
        return try {
            val res: PostureHistory = client.get {
                url(ApiRoutes.DASHBOARD)
                bearerAuth(token)
            }.body()

            AuthResponse.Success(res)
        }
        catch(e: Exception){
            AuthResponse.Failure(e)
        }
    }

    override suspend fun sendCapture(capture: PostureCapture): AuthResponse<WorkoutRes> {
        return try {
           val res: WorkoutRes = client.post {
            url(ApiRoutes.DASHBOARD)
            contentType(ContentType.Application.Json)
            setBody(capture)
        }.body()

            AuthResponse.Success(res)
        }
        catch(e: ClientRequestException) {
            e.printStackTrace()
            AuthResponse.Failure(e)
        }

        catch(e: Exception) {
            e.printStackTrace()
            AuthResponse.Failure(e)

        }


    }
}