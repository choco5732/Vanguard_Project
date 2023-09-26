package com.jess.camp.data.repository

import com.jess.camp.presentation.todo.home.TodoModel
import com.jess.camp.presentation.todo.home.TodoRepository
import java.util.concurrent.atomic.AtomicLong

class TodoRepositoryImpl(
    private val idGenerate: AtomicLong
) : TodoRepository {

    private val list = mutableListOf<TodoModel>()

    override fun getTestData(): List<TodoModel> {
        list.addAll(
            arrayListOf<TodoModel>().apply {
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
        )

        return ArrayList<TodoModel>(list)
    }

    override fun addTodoItem(
        item: TodoModel?
    ): List<TodoModel> {
        if (item == null) {
            return list
        }

        list.add(
            item.copy(
                id = idGenerate.getAndIncrement()
            )
        )

        return ArrayList<TodoModel>(list)
    }

    override fun modifyTodoItem(
        item: TodoModel?
    ): List<TodoModel> {

        fun findIndex(item: TodoModel?): Int {
            // 같은 id 를 찾음
            val findTodo = list.find {
                it.id == item?.id
            }

            // 찾은 model 기준으로 index 를 찾음
            return list.indexOf(findTodo)
        }

        if (item == null) {
            return list
        }

        // position 이 null 이면 indexOf 실시
        val findPosition = findIndex(item)
        if (findPosition < 0) {
            return list
        }

        list[findPosition] = item
        return ArrayList<TodoModel>(list)

    }

    override fun removeTodoItem(position: Int?): List<TodoModel> {
        if (position == null || position < 0) {
            return list
        }

        list.removeAt(position)
        return ArrayList<TodoModel>(list)
    }
}