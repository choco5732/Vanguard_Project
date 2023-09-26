package com.jess.camp.presentation.todo.home

interface TodoRepository {

    fun getTestData(): List<TodoModel>
    fun addTodoItem(item: TodoModel?): List<TodoModel>
    fun modifyTodoItem(item: TodoModel?): List<TodoModel>
    fun removeTodoItem(position: Int?): List<TodoModel>

}