package com.jess.camp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.camp.bookmark.BookmarkModel
import com.jess.camp.bookmark.toTodoModel
import com.jess.camp.todo.home.TodoModel
import com.jess.camp.todo.home.toBookmarkModel

class MainSharedViewModel : ViewModel() {

    private val _event: MutableLiveData<MainSharedEvent> = MutableLiveData()
    val event: LiveData<MainSharedEvent> get() = _event

    /**
     * isBookmark 인 아이템을 filter 합니다.
     */
    fun updateBookmarkItems(items: List<TodoModel>?) {
        items?.map {
            it.toBookmarkModel()
        }?.filter {
            it.isBookmark
        }?.also {
            _event.value = MainSharedEvent.UpdateBookmarkItems(it)
        }
    }

    fun updateTodoItem(item: BookmarkModel) {
        _event.value = MainSharedEvent.UpdateTodoItem(item.toTodoModel())
    }
}