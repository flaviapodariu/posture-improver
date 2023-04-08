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
    suspend fun insertCaptures(captures: List<CaptureEntity>)

    @Query("SELECT * FROM captures")
    fun getAllCaptures(): Flow<List<CaptureEntity>>

    @Query("SELECT * FROM captures WHERE date = :date")
    fun getAllCapturesByDate(date: LocalDate): Flow<List<CaptureEntity>>

    @Query("DELETE FROM captures")
    fun deleteAllCaptures()

}