package com.jess.camp.retorift

import com.jess.camp.R
import com.jess.camp.SpartaApp
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format(
                    SpartaApp.getApp().getString(R.string.kakao_rest_api_key)
                )
            ).build()
        return chain.proceed(newRequest)
    }
}