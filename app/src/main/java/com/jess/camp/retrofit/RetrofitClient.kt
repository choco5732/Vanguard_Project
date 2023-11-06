package com.jess.camp.retrofit

import com.google.gson.GsonBuilder
import com.jess.camp.data.remote.SearchRemoteDatasource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getDateFormatGsonBuilder()))
            .build()

    }

    // date 타입으로 뽑아줌
    private fun getDateFormatGsonBuilder() = GsonBuilder()
        .setDateFormat("YYYY-MM-DD'T'hh:mm:ss")
        .create()

    private val search: SearchRemoteDatasource by lazy {
        retrofit.create(SearchRemoteDatasource::class.java)
    }
}



/**
 *Retrofit.Builder()
.baseUrl(BASE_URL)
.client(okHttpClient)
.addConverterFactory(GsonConverterFactory.create())
.addConverterFactory(GsonConverterFactory.create(getDateFormatGsonBuilder()))
.build()
.create(SearchRemoteDatasource::class.java)

private val search: SearchRemoteDatasource by lazy {
retrofit.create(SearchRemoteDatasource::class.java)
}
위, 아래 코드는 동일한 코드일까?
 *
 **/