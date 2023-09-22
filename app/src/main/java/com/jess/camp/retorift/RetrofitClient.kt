package com.jess.camp.retorift

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

    private fun getDateFormatGsonBuilder() = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    val search: SearchRemoteDatasource by lazy {
        retrofit.create(SearchRemoteDatasource::class.java)
    }


}