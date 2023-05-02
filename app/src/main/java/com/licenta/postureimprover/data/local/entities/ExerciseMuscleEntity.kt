package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseMuscle", foreignKeys = arrayOf(
        ForeignKey(
            entity= ExerciseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
)
data class ExerciseMuscleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var exerciseId: Int,
    var muscle: String,
    var type: String
)