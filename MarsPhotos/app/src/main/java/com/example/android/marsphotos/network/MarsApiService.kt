package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    //Moshi를 사용하여 converter를 가져오자.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL) //웹 서비스의 기본 URI를 추가
    .build()  //retrofit 객체 만듦

//기본 URL(Retrofit 빌더에서 정의함)에 엔드포인트 photos를 추가해서 가져옴.
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto> //변경
    //참고-suspend 키워드를 붙여서 정지함수로 만들면 코루틴 내에서 이 메서드 호출 가능
}

/*
싱글톤 패턴은 객체의 인스턴스가 하나만 생성되도록 보장함.
Retrofit 객체에서 create() 함수를 호출하는 데는 리소스가 많이 들고,
앱에는 Retrofit API 서비스의 인스턴스가 하나만 필요함. 그러니까 싱글톤 객체로 만들자.
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}