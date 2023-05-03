package com.licenta.postureimprover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.licenta.postureimprover.data.local.entities.ExerciseMuscleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseMuscleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseMuscleTypes(types: List<ExerciseMuscleEntity>)

    @Query("SELECT * FROM exercisemuscle")
    fun getAllExerciseMuscleTypes() : Flow<List<ExerciseMuscleEntity>>

    @Query("SELECT * FROM exercisemuscle WHERE exerciseId = :id")
    fun getAllMusclesWorkedForExercise(id: Int) : Flow<List<ExerciseMuscleEntity>>

    @Query("DELETE FROM exercisemuscle")
    fun deleteAllExerciseMuscleTypes()
}