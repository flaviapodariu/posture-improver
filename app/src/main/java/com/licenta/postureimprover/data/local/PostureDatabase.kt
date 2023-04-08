package com.licenta.postureimprover.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.dao.WorkoutDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.local.entities.LocalDateConverter

@Database(
    entities = [CaptureEntity::class, ExerciseEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to= 2)
    ]
)

@TypeConverters(LocalDateConverter::class)
abstract class PostureDatabase: RoomDatabase() {

    abstract val capturesDao: CapturesDao
    abstract val workoutDao: WorkoutDao
}