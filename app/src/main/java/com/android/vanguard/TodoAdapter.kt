package com.android.vanguard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vanguard.databinding.FragmentTodoItemBinding

// todo 리사이클러뷰 어댑터
class TodoAdapter(val list : List<Todo>) : RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {

        val binding = FragmentTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val todo = list[position]
        holder.setItem(todo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class Holder(private val binding: FragmentTodoItemBinding)  : RecyclerView.ViewHolder(binding.root){

    fun setItem(todo : Todo){
        binding.textView.text = "${todo.text}"
    }
}