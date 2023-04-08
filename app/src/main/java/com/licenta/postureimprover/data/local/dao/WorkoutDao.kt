package com.licenta.postureimprover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: List<ExerciseEntity>)

    @Query("SELECT * FROM workout")
    fun getWorkout(): Flow<List<ExerciseEntity>>

    @Query("DELETE FROM workout")
    fun deleteWorkout()
}