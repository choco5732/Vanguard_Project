package com.jess.camp.presentation.todo.home

sealed interface TodoEvent {

    data class OpenContent(
        val position: Int,
        val item: TodoModel
    ) : TodoEvent
}