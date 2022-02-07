package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto (
    val id: String,
    //img_src라는 키를 imgSrcUrl 이라는 변수에 할당.. 카멜표기법으로 하기위해!
    @Json(name = "img_src") val imgSrcUrl: String
)