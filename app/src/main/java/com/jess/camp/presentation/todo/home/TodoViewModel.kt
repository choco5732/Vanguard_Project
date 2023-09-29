package com.jess.camp.presentation.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jess.camp.data.repository.TodoRepositoryImpl
import com.jess.camp.util.SingleLiveEvent
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val list: LiveData<List<TodoModel>> get() = _list

    private val _event: SingleLiveEvent<TodoEvent> = SingleLiveEvent()
    val event: LiveData<TodoEvent> get() = _event

    init {
        _list.value = repository.getTestData()
    }

    /**
     * TodoModel 아이템을 추가합니다.
     */
    fun addTodoItem(
        item: TodoModel?
    ) {
        _list.value = repository.addTodoItem(item)
    }

    fun modifyTodoItem(
        item: TodoModel?
    ) {
        _list.value = repository.modifyTodoItem(item)
    }

    fun removeTodoItem(
        position: Int?
    ) {
        _list.value = repository.removeTodoItem(position)
    }

    fun onClickItemForEdit(
        position: Int,
        item: TodoModel
    ) {
        _event.value = TodoEvent.OpenContent(position, item)
    }
}

class TodoViewModelFactory : ViewModelProvider.Factory {

    private val repository = TodoRepositoryImpl(
        AtomicLong(1L)
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}