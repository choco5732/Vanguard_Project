package com.jess.camp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jess.camp.databinding.TodoItemBinding

class TodoListAdapter(
    private val onClickItem: (Int, TodoModel) -> Unit
) : ListAdapter<TodoModel, TodoListAdapter.ViewHolder>( //  기존 : RecyclerView.Adapter

    object : DiffUtil.ItemCallback<TodoModel>() {  // 아이템의 속성이 같은지 비교 ( 아이디 )
        override fun areItemsTheSame(
            oldItem: TodoModel,
            newItem: TodoModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(  // TodoModel 자체가 같은지 비교
            oldItem: TodoModel,
            newItem: TodoModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = list[position]
        val item = getItem(position)
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
//}

//    private val list = ArrayList<TodoModel>()

//    fun addItems(items: List<TodoModel>) {
//
//        submitList(items)
//        list.addAll(items)
//        notifyDataSetChanged()
//    }

//    fun addItem(todoModel: TodoModel?) {
//
//        val list = currentList.toMutableList()
//        list.add(todoModel)
//        submitList(list)

        // 기존에 사용하던 리스트를 그대로 사용하면 반영이 안됨.
        // listAdapter는 submitList를 할때 새로운 어레이리스트를 생성해서 넣어야 함. -> toMutableList()를 타고 보면 새로운 리스트를 반환해주는 걸 확인할 수 있다.
//        todoModel?.let {
//            list.add(todoModel)
//            notifyItemChanged(list.size - 1)
//        }
//    }

//    fun modifyItem(
//        position: Int?,
//        todoModel: TodoModel?
//    ) {
//        if (position == null || todoModel == null) {
//            return
//        }
//
//        val list = currentList.toMutableList()
//        list[position] = todoModel
//        submitList(list)

//        list[position] = todoModel
//        notifyItemChanged(position)
//    }

//    fun removeItem(
//        position: Int?
//    ) {
//        if (position == null) {
//            return
//        }

//        val list = currentList.toMutableList()
//        list.removeAt(position)
//        submitList(list)
//        list.removeAt(position)
//        notifyItemRemoved(position)
//    }

//    override fun getItemCount(): Int {
//        return list.size
//    }
