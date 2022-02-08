package com.example.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    //fun getAll(): List<Schedule>
    fun getAll(): Flow<List<Schedule>> //변경

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    //fun getByStopName(stopName: String): List<Schedule>
    fun getByStopName(stopName: String): Flow<List<Schedule>> //변경
}