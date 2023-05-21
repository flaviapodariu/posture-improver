package com.licenta.postureimprover.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CapturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCapture(capture: CaptureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaptures(captures: List<CaptureEntity>)

    @Query("SELECT * FROM captures where userId = :userId")
    fun getAllCaptures(userId: Int): Flow<List<CaptureEntity>>

    @Query("SELECT * FROM captures WHERE date = :date AND userId = :userId")
    fun getAllCapturesByDate(date: LocalDate, userId: Int): Flow<List<CaptureEntity>>

    @Query("DELETE FROM captures WHERE userId = :userId")
    fun deleteAllCaptures(userId: Int)

}