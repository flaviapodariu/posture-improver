package com.licenta.postureimprover.data.local

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.dao.ExercisesDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.local.entities.ExerciseMuscleEntity
import com.licenta.postureimprover.data.local.entities.LocalDateConverter

@Database(
    entities = [CaptureEntity::class, ExerciseEntity::class, ExerciseMuscleEntity::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to= 2),
        AutoMigration(from = 2, to= 3, spec = PostureDatabase.MigrationSpecFrom2To3::class),
        AutoMigration(from = 3, to= 4, spec = PostureDatabase.MigrationSpecFrom3To4::class),
        AutoMigration(from = 4, to= 5, spec = PostureDatabase.MigrationSpecFrom4To5::class)
    ]
)

@TypeConverters(LocalDateConverter::class)
abstract class PostureDatabase: RoomDatabase() {

    abstract val capturesDao: CapturesDao
    abstract val exercisesDao: ExercisesDao

    @RenameColumn(tableName = "Workout", fromColumnName = "targetedMuscle", toColumnName = "targetedMuscles")
    class MigrationSpecFrom2To3 : AutoMigrationSpec

    @RenameTable(fromTableName = "Workout", toTableName = "Exercises")
    class MigrationSpecFrom3To4 : AutoMigrationSpec

    @DeleteColumn.Entries(
        DeleteColumn(tableName = "Exercises", columnName = "lordosisScore"),
        DeleteColumn(tableName = "Exercises", columnName = "headFwdScore"),
        DeleteColumn(tableName = "Exercises", columnName = "roundedShScore")
    )
    class MigrationSpecFrom4To5 : AutoMigrationSpec
}