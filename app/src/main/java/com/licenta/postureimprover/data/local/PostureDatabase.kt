package com.licenta.postureimprover.data.local

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.dao.WorkoutDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.local.entities.LocalDateConverter

@Database(
    entities = [CaptureEntity::class, ExerciseEntity::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to= 2),
        AutoMigration(from = 2, to= 3, spec = PostureDatabase.MigrationSpecFrom2To3::class)
    ]
)

@TypeConverters(LocalDateConverter::class)
abstract class PostureDatabase: RoomDatabase() {

    abstract val capturesDao: CapturesDao
    abstract val workoutDao: WorkoutDao

    @RenameColumn(tableName = "Workout", fromColumnName = "targetedMuscle", toColumnName = "targetedMuscles")
    class MigrationSpecFrom2To3: AutoMigrationSpec
}