package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.schedule.AppDatabase

class BusScheduleApplication: Application() {
    //getDatabase()의 결과를 보유할 lazy 속성
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this)}
}