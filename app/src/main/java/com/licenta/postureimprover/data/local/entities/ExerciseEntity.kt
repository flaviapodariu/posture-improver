package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var description: String,
    var imageUrl: String,
    var reps: Int
)
