package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    //onConflict: 충돌 발생시 Room이 뭘해야하는지 지정
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)  //정지함수로 만들어 코루틴에서 호출할 수 있게..

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    //삽입,업뎃,삭제는 전용 주석(@)이 존재함.
    //근데 검색 등은 전용 주석 없으니까 @Query(쿼리문)의 형태로 써야 한다.
    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item> //데이터 바뀌었을때 바로 대응 위해..Flow(지속성)

    //윗놈은 데이터 하나 반환, 얘는 전체 반환
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>
}