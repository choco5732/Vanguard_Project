package com.jess.camp.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    // 네트워크 데이터 통신을 할때마다 캐치해서 특정한 행위를 하고자 할때 인터셉터를 꽂아 넣는다?
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = cha
    }
}