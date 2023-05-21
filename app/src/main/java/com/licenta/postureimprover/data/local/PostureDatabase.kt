package com.licenta.postureimprover.data.local

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.dao.ExerciseMuscleDao
import com.licenta.postureimprover.data.local.dao.ExercisesDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.data.local.entities.ExerciseMuscleEntity
import com.licenta.postureimprover.data.local.entities.LocalDateConverter

@Database(
    entities = [CaptureEntity::class, ExerciseEntity::class, ExerciseMuscleEntity::class],
    version = 6,
    autoMigrations = [
        AutoMigration(from = 1, to= 2),
        AutoMigration(from = 2, to= 3, spec = PostureDatabase.MigrationSpecFrom2To3::class),
        AutoMigration(from = 3, to= 4, spec = PostureDatabase.MigrationSpecFrom3To4::class),
        AutoMigration(from = 4, to= 5, spec = PostureDatabase.MigrationSpecFrom4To5::class),
    AutoMigration(from = 5, to= 6, spec = PostureDatabase.MigrationSpecFrom5To6::class)
    ]
)

@TypeConverters(LocalDateConverter::class)
abstract class PostureDatabase: RoomDatabase() {

    abstract val capturesDao: CapturesDao
    abstract val exercisesDao: ExercisesDao
    abstract val exercisesMuscleDao: ExerciseMuscleDao

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

    @DeleteColumn(tableName = "Exercises", columnName = "targetedMuscles")
    class MigrationSpecFrom5To6 : AutoMigrationSpec

    companion object {
        val migrationFrom6To7 = object: Migration(6,7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE exercises ADD COLUMN userId INTEGER")
                database.execSQL("ALTER TABLE captures ADD COLUMN userId INTEGER")
            }
        }
    }


}