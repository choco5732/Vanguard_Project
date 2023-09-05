package com.jess.camp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jess.camp.databinding.TodoItemBinding

class TodoListAdapter(
    private val onClickItem: (Int, TodoModel) -> Unit,
    private val onBookmarkChecked: (Int, TodoModel) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val list = ArrayList<TodoModel>()

    fun addItem(item: TodoModel?) {
        item?.let {
            list.add(item)
            notifyItemChanged(list.size - 1)
        }
    }

    fun addItems(items: List<TodoModel>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun modifyItem(
        position: Int?,
        todoModel: TodoModel?
    ) {
        if (position == null || todoModel == null) {
            return
        }
        list[position] = todoModel
        notifyItemChanged(position)
    }

    fun modifyItem2(position: Int, checked: Boolean) {

    }

    fun removeItem(
        position: Int?
    ) {
        if (position == null) {
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
            onClickItem,
            onBookmarkChecked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: TodoItemBinding,
        private val onClickItem: (Int, TodoModel) -> Unit,
        private val onBookmarkChecked: (Int, TodoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoModel) = with(binding) {
            title.text = item.title
            description.text = item.description
            bookmark.isChecked = item.isBookmark

            // 아이템 클릭
            container.setOnClickListener {
                onClickItem(
                    adapterPosition,
                    item
                )
            }

            // 북마크 클릭
            bookmark.setOnCheckedChangeListener { _, isChecked ->
                // 현재 바인딩된 아이템과 checked 된 값 비교 후 전달
                if (item.isBookmark != isChecked) {
                    onBookmarkChecked(
                        adapterPosition,
                        item.copy(
                            isBookmark = isChecked
                        )
                    )
                }
            }
        }
    }

}