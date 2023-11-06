package com.jess.camp.presentation.todo.home

import java.util.concurrent.atomic.AtomicLong

interface TodoRepository {
    fun getTestData(): List<TodoModel>
    fun addTodoItem(item: TodoModel?): List<TodoModel>
    fun addTodoItem2(item: TodoModel?): List<TodoModel>
    fun modifyTodoItem(item: TodoModel?): List<TodoModel>
    fun removeTodoItem(position: Int?): List<TodoModel>
}