package com.licenta.postureimprover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM exercises")
    fun getAllExercises() : Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getExerciseById(id: Int) : Flow<ExerciseEntity>

    @Query("DELETE FROM exercises")
    fun deleteAllExercises()
}