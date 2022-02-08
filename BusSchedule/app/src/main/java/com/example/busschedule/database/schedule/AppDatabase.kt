package com.example.busschedule.database.schedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//RoomDatabase에서 상속받는 추상 클래스 AppDatabase
@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    //데이터베이스 객체는 한개만 있으면 됨
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        // AppDatabase 인스턴스를 반환하는 함수

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db") //데이터 로드
                    .build()
                INSTANCE = instance

                instance
            }
        }


    }
}