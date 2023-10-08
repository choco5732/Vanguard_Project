package com.jess.camp.retrofit

import okhttp3.OkHttpClient

object RetrofitClient {

    private const val BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }
}