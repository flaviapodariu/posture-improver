package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import java.time.LocalDate

@Entity(tableName = "Captures")
data class CaptureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    var headForward: Float,
    var lordosis: Float,
    var roundedShoulders: Float,
    var date: LocalDate
)

fun CaptureEntity.asNetworkModel() = CaptureRes(
    id = id,
    userId = userId,
    headForward = headForward,
    lordosis = lordosis,
    roundedShoulders = roundedShoulders,
    date = date
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