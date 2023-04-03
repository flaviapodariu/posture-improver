package com.licenta.postureimprover.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.licenta.postureimprover.data.local.dao.CapturesDao
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.local.entities.LocalDateConverter

@Database(
    entities = [CaptureEntity::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class PostureDatabase: RoomDatabase() {

    abstract val capturesDao: CapturesDao
}