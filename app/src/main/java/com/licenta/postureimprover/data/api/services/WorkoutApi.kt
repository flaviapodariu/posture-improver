package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.api.dto.request.UserExercise
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.util.Task

interface WorkoutApi {
    suspend fun getWorkoutForUser(token: String) : Task<List<WorkoutRes>>
    suspend fun getExerciseById(exerciseId: Int) : Task<Exercise>

    suspend fun sendBulkExercises(joinTableList: List<UserExercise>, token: String) : Task<Boolean>?
}