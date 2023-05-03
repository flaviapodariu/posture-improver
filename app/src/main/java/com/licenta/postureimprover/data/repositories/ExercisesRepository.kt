package com.licenta.postureimprover.data.repositories

import androidx.room.withTransaction
import com.licenta.postureimprover.data.api.dto.response.asEntity
import com.licenta.postureimprover.data.api.dto.response.asExerciseEntity
import com.licenta.postureimprover.data.api.services.WorkoutApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.util.networkBoundTask
import javax.inject.Inject

class ExercisesRepository @Inject constructor(
    private val api: WorkoutApi,
    private val db: PostureDatabase
) {
    private val exDao = db.exercisesDao
    private val exMuscleDao = db.exercisesMuscleDao

    fun getExercises(token: String) = networkBoundTask(
        query = {
            exDao.getAllExercises()
        },
        fetch = {
            api.getWorkoutForUser(token)
        },
        saveFetchResult = {
            db.withTransaction {
                exDao.deleteAllExercises()
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

    fun getExerciseById(id: Int) = exDao.getExerciseById(id)

    fun getMusclesWorkedForExercise(id: Int) = exMuscleDao.getAllMusclesWorkedForExercise(id)
}