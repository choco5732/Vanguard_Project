package com.jess.camp.todo.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jess.camp.databinding.TodoItemBinding

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val list = ArrayList<TodoModel>()
    private lateinit var binding: TodoItemBinding

    interface ItemClick {
        fun onClick(view: View, position: Int, list: TodoModel)
    }

    var itemClick: ItemClick? = null

    fun addItem(todoModel: TodoModel?) {
        if (todoModel == null) {
            return
        }

        list.add(todoModel)
        notifyItemChanged(list.size - 1)
//        notifyDataSetChanged()
    }

    fun modifyItem(todoModel: TodoModel?, position: Int?){
        Log.d("list","${todoModel}")


        if (todoModel != null) {
            list[position!!].title = todoModel.title
            list[position!!].description = todoModel.description
        }
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int?){
        if (position != null) {
            list.removeAt(position)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position, list[position])
        }

        val item = list[position]
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: TodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoModel) = with(binding) {
            title.text = item.title
            description.text = item.description
        }
    }

}