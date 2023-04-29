package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.response.asExerciseEntity
import com.licenta.postureimprover.data.api.services.WorkoutApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.util.networkBoundTask
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val api: WorkoutApi,
    private val db: PostureDatabase
) {
    private val dao = db.workoutDao

    fun getWorkout(token: String) = networkBoundTask(
        query = {
            dao.getWorkout()
        },
        fetch = {
            api.getWorkoutForUser(token)
        },
        saveFetchResult = {
            db.withTransaction {
                dao.deleteWorkout()
                dao.insertWorkout( it.data?.map { it.asExerciseEntity() }!!)
            }
        }
    )

    fun getExerciseById(id: Int) = dao.getExerciseById(id)
}