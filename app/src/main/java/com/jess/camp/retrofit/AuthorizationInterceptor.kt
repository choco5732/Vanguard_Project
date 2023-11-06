package com.jess.camp.retrofit

import com.jess.camp.R
import com.jess.camp.SpartaApp
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    // 네트워크 통신을 할때마다 캐치해서 특정행위를 하고자 할때 interceptor를 꼽아줌
    // 즉, 매번 통신할 때마다 헤더에 얘를 넣겟다!
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KaKaoAK %s".format(
                    SpartaApp.getApp().getString(R.string.kakao_rest_api_key)
                )
                // 왜 strings_api_key를 바로 참조하지 않고 SpartaApp를 한 다리 걸치는 걸까?
            ).build()
        return chain.proceed(newRequest)
    }
}