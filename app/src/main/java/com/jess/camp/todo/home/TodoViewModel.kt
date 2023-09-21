package com.jess.camp.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData() // 뷰모델 내부적으로 컨트롤 할때 쓰는 데이터
    val list: LiveData<List<TodoModel>> get() = _list // 오로지 읽기만 가능한 상태의 변수 (액티비티나 프래그먼트에서 사용하는 변수)

    // 쓰레드 세이프를 위한 넘버 클래스(AtomicLong 클래스).. id를 부여할 값
    private val idGenerate = AtomicLong(1L)

    init {
        _list.value = arrayListOf<TodoModel>().apply {
            for ( i in 0 until 3) {
                add(
                    TodoModel(
                        idGenerate.getAndIncrement(),
                        "title $i",
                        "description $i"
                    )
                )
            }
        }
    }

    // 1. MainActivity에서 addTodoLauncher를 통해 데이터가 들어옴
    // 2. setTodoContent 통해 TodoFragment로 데이터를 전달함
    // 3. setTodoContent에서 viewModel을 생성하고 viewModel에서 최종적으로 데이터 처리를 해줌.
    fun addTodoItem(todoModel: TodoModel?) {
        if (todoModel == null) {
            return
        }
        // copy? 데이터함수에서 제공하는 기능으로 깊은복사가 가능한것 같다
        val currentList = list.value?.toMutableList()
        currentList?.add(todoModel.copy(
            id = idGenerate.getAndIncrement()
        ))
        _list.value = currentList
    }

    fun modifyTodoItem(position: Int?, todoModel: TodoModel?) {

        fun findIndex(todoModel: TodoModel): Int? {
            val currentList = list.value?.toMutableList()
            val findTodoById = currentList?.find {
                it.id == todoModel.id
            }
            return currentList?.indexOf(findTodoById)  // indexOf : currentList의 특정요소를 가진 녀석의 인덱스를 찾아줌
        }

        if (todoModel == null) {
            return
        }

        // position의 값이 안들어올 경우 find로 값을 강제로 찾아줄 수 있다.
        val findPosition = position ?: findIndex(todoModel)
        if (findPosition == null || findPosition <0) {
            return
        }

        val currentList = list.value?.toMutableList() ?: return
        currentList[findPosition] = todoModel
        _list.value = currentList

    }

    fun removeTodoItem(position: Int?) {
        if (position == null || position < 0) {
            return
        }

        val currentList = list.value?.toMutableList()
        currentList?.removeAt(position)
        _list.value = currentList
    }
}
