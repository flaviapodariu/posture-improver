package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.response.asExerciseEntity
import com.licenta.postureimprover.data.api.services.WorkoutApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.util.networkBoundTask
import javax.inject.Inject

class ExercisesRepository @Inject constructor(
    private val api: WorkoutApi,
    private val db: PostureDatabase
) {
    private val dao = db.exercisesDao

    fun getExercises(token: String) = networkBoundTask(
        query = {
            dao.getAllExercises()
        },
        fetch = {
            api.getWorkoutForUser(token)
        },
        saveFetchResult = {
            db.withTransaction {
                dao.deleteAllExercises()
                dao.insertExercises( it.data?.map { it.asExerciseEntity() }!!)
            }
        }
    )

    fun getExerciseById(id: Int) = dao.getExerciseById(id)
}