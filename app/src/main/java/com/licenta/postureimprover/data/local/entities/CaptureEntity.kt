package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import java.time.LocalDate

@Entity(tableName = "Captures")
data class CaptureEntity(
    val userId: Int?,
    var headForward: Float,
    var lordosis: Float,
    var roundedShoulders: Float,
    var date: LocalDate,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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

fun CaptureEntity.asCaptureReq() = CaptureReq(
    headForward = this.headForward,
    roundedShoulders = this.roundedShoulders,
    lordosis = this.lordosis
)