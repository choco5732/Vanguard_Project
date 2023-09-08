package com.jess.camp.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val list: LiveData<List<TodoModel>> get() = _list

    // id 를 부여할 값
    private val idGenerate = AtomicLong(1L)

    init {
        _list.value = arrayListOf<TodoModel>().apply {
            for (i in 0 until 3) {
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

    /**
     * TodoModel 아이템을 추가합니다.
     */
    fun addTodoItem(
        todoModel: TodoModel?
    ) {
        if (todoModel == null) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        _list.value = currentList.apply {
            add(
                todoModel.copy(
                    id = idGenerate.getAndIncrement()
                )
            )
        }
    }

    fun modifyTodoItem(
        position: Int?,
        todoModel: TodoModel?
    ) {

        fun findIndex(item: TodoModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            // 같은 id 를 찾음
            val findTodo = currentList.find {
                it.id == item?.id
            }

            // 찾은 model 기준으로 index 를 찾음
            return currentList.indexOf(findTodo)
        }

        if (todoModel == null) {
            return
        }

        // position 이 null 이면 indexOf 실시
        val findPosition = position ?: findIndex(todoModel)
        if (findPosition < 0) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPosition] = todoModel
        _list.value = currentList
    }

    fun removeTodoItem(position: Int?) {
        if (position == null) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(position)
        _list.value = currentList
    }
}