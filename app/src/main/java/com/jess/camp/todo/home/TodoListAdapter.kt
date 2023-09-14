package com.jess.camp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jess.camp.databinding.TodoItemBinding

class TodoListAdapter(
    private val onClickItem: (Int, TodoModel) -> Unit // 현업에서는 인터페이스 보다는 하이어드 펑션을 이용하는 추세!
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val list = ArrayList<TodoModel>()

    fun addItem(todoModel: TodoModel?) {
        if (todoModel == null) {
            return
        }

        list.add(todoModel)
        notifyItemChanged(list.size - 1)
    }

    fun editTodoItem(
        position: Int,
        item: TodoModel?
    ) {
        if (position < 0) {
            return
        }

        if (item == null) {
            return
        }

        list[position]= item
        notifyItemChanged(position) // 전체가 아닌 해당 위치의 아이템만 변경
    }
    fun deleteTodoItem(position: Int) {
        if (position < 0) {
            return
        }

        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }



    class ViewHolder(
        private val binding: TodoItemBinding,
        private val onClickItem: (Int, TodoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoModel) = with(binding) {
            title.text = item.title
            description.text = item.description

            container.setOnClickListener {
                onClickItem(
                    adapterPosition,
                    item
                )
            }
        }
    }
}