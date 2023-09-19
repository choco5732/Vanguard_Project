package com.jess.camp.main

import com.jess.camp.bookmark.BookmarkModel
import com.jess.camp.todo.home.TodoModel

sealed interface MainSharedEvent {

    data class UpdateBookmarkItems(
        val items: List<BookmarkModel>
    ) : MainSharedEvent

    data class UpdateTodoItem(
        val item: TodoModel
    ) : MainSharedEvent
}
