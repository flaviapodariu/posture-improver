package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate

@Entity(tableName = "Captures")
data class CaptureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var headForward: Float,
    var lordosis: Float,
    var roundedShoulders: Float,
    var date: LocalDate
)

class LocalDateConverter {
    @TypeConverter
    fun dateToString(date: LocalDate) : String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(string: String) : LocalDate {
        return LocalDate.parse(string)
    }
}