package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.ApiRoutes
import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.util.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class WorkoutApiImpl @Inject constructor(
    private val client: HttpClient
): WorkoutApi {
    override suspend fun getWorkoutForUser(token: String) : Task<List<WorkoutRes>> {

        return try {
            val res:List<WorkoutRes> = client.get {
                url(ApiRoutes.WORKOUT)
                bearerAuth(token)
            }.body()

            Task.Success(res)
        }
        catch (e: Exception) {
            e.printStackTrace()
            Task.Failure(e)
        }

    }

    override suspend fun getExerciseById(exerciseId: Int): Task<Exercise> {
        return try {
            val res:Exercise = client.get(ApiRoutes.EXERCISES) {
                url {
                    appendPathSegments("$exerciseId")
                }
            }.body()

            Task.Success(res)
        }
        catch(e: Exception) {
            e.printStackTrace()
            Task.Failure(e)
        }
    }
}