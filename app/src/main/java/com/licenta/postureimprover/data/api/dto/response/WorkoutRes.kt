package com.licenta.postureimprover.data.api.dto.response

import com.licenta.postureimprover.data.api.dto.Exercise
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.local.entities.ExerciseMuscleEntity
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRes(
    val exercise: Exercise,
    val reps: Int,
    var targets: List<ExerciseMuscleType>
)

@Serializable
data class ExerciseMuscleType(
    val muscle: String,
    val type: String
)

fun ExerciseMuscleType.asEntity(exerciseId: Int) = ExerciseMuscleEntity(
    exerciseId = exerciseId,
    muscle = muscle,
    type = type
)
fun WorkoutRes.asExerciseEntity() = ExerciseEntity(
    id = exercise.id,
    name = exercise.name,
    description = exercise.description,
    imageUrl = exercise.imageUrl,
    reps = reps
)
