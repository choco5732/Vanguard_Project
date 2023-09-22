package com.jess.camp.todo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.atomic.AtomicLong

// 매개변수가 있는 뷰모델을 생성하는 용도로 뷰모델팩토리를 쓴다.
class TodoViewModelFactory : ViewModelProvider.Factory {

    private val idGenerate = AtomicLong(1L) // 멤버변수 선언

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(idGenerate) as T
        } else {
            throw IllegalArgumentException("뷰모델 찾지 못함") // 사실 throw는 필요 없는데 인터넷에 많이 보여서 적용
        }
    }
}