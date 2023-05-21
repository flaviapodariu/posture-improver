package com.licenta.postureimprover.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.licenta.postureimprover.data.api.dto.request.UserExercise

@Entity(tableName = "Exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int?,
    var name: String,
    var description: String,
    var imageUrl: String,
    var reps: Int
)

fun ExerciseEntity.asUserExercise() = UserExercise(
    userId = this.userId!!,
    exerciseId = this.id,
    reps = this.reps
)