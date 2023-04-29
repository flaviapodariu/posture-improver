package com.licenta.postureimprover.data.api.dto.response

import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRes(
    val exercise: Exercise,
    val reps: Int
)

fun WorkoutRes.asExerciseEntity() = ExerciseEntity(
    id = exercise.id,
    name = exercise.name,
    description = exercise.description,
    lordosisScore = exercise.lordosisScore,
    headFwdScore = exercise.headFwdScore,
    roundedShScore = exercise.roundedShScore,
    targetedMuscles = exercise.targetedMuscles,
    imageUrl = exercise.imageUrl,
    reps = reps
)
