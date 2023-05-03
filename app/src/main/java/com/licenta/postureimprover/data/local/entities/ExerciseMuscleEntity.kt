package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.licenta.postureimprover.data.api.dto.response.ExerciseMuscleType

@Entity(tableName = "ExerciseMuscle", foreignKeys = [ForeignKey(
    entity= ExerciseEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("exerciseId"),
    onDelete = ForeignKey.CASCADE
)],
)
data class ExerciseMuscleEntity(
    var exerciseId: Int,
    var muscle: String,
    var type: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun ExerciseMuscleEntity.asExerciseMuscleType() = ExerciseMuscleType(
    muscle = muscle,
    type = type
)