package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.request.UserExercise
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.api.dto.response.asEntity
import com.licenta.postureimprover.data.api.dto.response.asExerciseEntity
import com.licenta.postureimprover.data.api.services.WorkoutApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.util.networkBoundTask
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExercisesRepository @Inject constructor(
    private val api: WorkoutApi,
    private val db: PostureDatabase
) {
    private val exDao = db.exercisesDao
    private val exMuscleDao = db.exercisesMuscleDao

    fun getExercises(token: String) = networkBoundTask(
        query = {
            exDao.getAllExercises(USER_ID)
        },
        fetch = {
            api.getWorkoutForUser(token)
        },
        saveFetchResult = {
            db.withTransaction {
                exDao.deleteAllExercises(USER_ID)
                exMuscleDao.deleteAllExerciseMuscleTypes()
                exDao.insertExercises( it.data?.map { it.asExerciseEntity() }!!)

                exMuscleDao.insertExerciseMuscleTypes(
                    it.data.flatMap { current ->
                        current.targets.map {
                            it.asEntity(current.exercise.id)
                        }
                    }
                )

            }
        }
    )

    suspend fun saveExercisesLocally(exercises: List<WorkoutRes>) {
        db.withTransaction {
            exDao.deleteAllExercises(USER_ID)
            exMuscleDao.deleteAllExerciseMuscleTypes()

            exDao.insertExercises( exercises.map { it.asExerciseEntity() })

            exMuscleDao.insertExerciseMuscleTypes(
                exercises.flatMap { current ->
                    current.targets.map {
                        it.asEntity(current.exercise.id)
                    }
                }
            )
        }
    }
    suspend fun getAllExercisesForCurrentUser() : List<ExerciseEntity> {
        return exDao.getAllExercises(USER_ID).first()
    }

    suspend fun sendBulkExercises(exList: List<UserExercise>, token: String) : Boolean {
        return api.sendBulkExercises(exList, token)?.data?: false
    }
    fun getExerciseById(id: Int) = exDao.getExerciseById(id)

    fun getMusclesWorkedForExercise(id: Int) = exMuscleDao.getAllMusclesWorkedForExercise(id)

    suspend fun deleteAllExercises() {
        withContext(Dispatchers.IO) {
            exMuscleDao.deleteAllExerciseMuscleTypes()
            exDao.deleteAllExercises(USER_ID)
        }
    }
}