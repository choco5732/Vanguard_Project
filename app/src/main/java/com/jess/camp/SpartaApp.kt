package com.jess.camp

import android.app.Application

class SpartaApp : Application() { // 전역으로 사용할 수 있는 Application 클래스
    companion object {
        // 메인 메모리에만 저장되며, 멀티 쓰레드 환경에서 메인 메모리의 값을 참조하므로 변수 값 불일치 문제를 해결함
        // 이게 무슨 소리지..?
        @Volatile
        private lateinit var app: SpartaApp

        @JvmStatic
        fun getApp(): SpartaApp {
            return app
            // 위 lateinit을 초기화 해주지 않았는데 어떻게 사용할 수 있는걸까?
        }
    }

    override fun onCreate() {
        app = this
        super.onCreate()
    }
}