package com.jess.camp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.camp.presentation.bookmark.BookmarkModel
import com.jess.camp.presentation.bookmark.toTodoModel
import com.jess.camp.presentation.todo.home.TodoModel
import com.jess.camp.presentation.todo.home.toBookmarkModel

class MainSharedViewModel : ViewModel() {

    private val _todoEvent: MutableLiveData<MainSharedEventForTodo> = MutableLiveData()
    val todoEvent: LiveData<MainSharedEventForTodo> get() = _todoEvent

    private val _bookmarkEvent: MutableLiveData<MainSharedEventForBookmark> = MutableLiveData()
    val bookmarkEvent: LiveData<MainSharedEventForBookmark> get() = _bookmarkEvent

    /**
     * isBookmark 인 아이템을 filter 합니다.
     */
    fun updateBookmarkItems(items: List<TodoModel>?) {
        items?.map {
            it.toBookmarkModel()
        }?.filter {
            it.isBookmark
        }?.also {
            _bookmarkEvent.value = MainSharedEventForBookmark.UpdateBookmarkItems(it)
        }
    }

    fun updateTodoItem(item: BookmarkModel) {
        _todoEvent.value = MainSharedEventForTodo.UpdateTodoItem(item.toTodoModel())
    }
}

sealed interface MainSharedEventForTodo {

    data class UpdateTodoItem(
        val item: TodoModel
    ) : MainSharedEventForTodo
}


sealed interface MainSharedEventForBookmark {

    data class UpdateBookmarkItems(
        val items: List<BookmarkModel>
    ) : MainSharedEventForBookmark
}
