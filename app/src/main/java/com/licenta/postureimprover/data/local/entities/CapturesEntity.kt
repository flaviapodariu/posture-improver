package com.licenta.postureimprover.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Captures")
data class CapturesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var headForward: Float,
    var lordosis: Float,
    var roundedShoulders: Float,
    var date: LocalDate
)
