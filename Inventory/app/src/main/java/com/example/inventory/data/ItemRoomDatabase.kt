package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
@Database는 매개변수 3개가 필요함.
entities: 아까만든 item 테이블.
version: 데이터베이스 스키마 구조 바뀔때마다 +1 해야함. 초기상태니까 1
exportSchema: false로 두면 스키마 버전 기록 백업 안함.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    //이놈이 dao를 알아야 지지고 볶고 하겠지..ItemDao를 반환하는 추상 함수를 선언.
    abstract fun itemDao(): ItemDao
    // 참고-Dao는 여러개일 수 있다.

    //데이터베이스는 만드는데 비용이 많이듦. 단일 디비를 유지하자.
    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {
            //db 인스턴스가 있으면 걜 반환, 없으면 synchronized 블록 실행해서 만들기
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

    }
}

}