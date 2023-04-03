package com.licenta.postureimprover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CapturesDao {

    @Insert
    suspend fun insertCapture(capture: CaptureEntity)

    @Query("SELECT * FROM captures WHERE userId = :userId")
    fun getAllCaptures(userId: Int): Flow<List<CaptureEntity>>

    @Query("SELECT * FROM captures WHERE userId = :userId AND date = :date")
    fun getAllCapturesByDate(userId: Int, date: LocalDate): Flow<List<CaptureEntity>>

}