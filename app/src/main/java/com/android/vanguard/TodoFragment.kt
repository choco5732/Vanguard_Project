package com.android.vanguard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vanguard.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    // 추가코드1
    val binding by lazy {FragmentTodoBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val adapter = TodoAdapter(loadData()) // 추가코드3 data
        binding.recyclerView.adapter = adapter // 추가코드4 binding
        binding.recyclerView.layoutManager = LinearLayoutManager(context) // 추가코드5 binding

        return binding.root
    }

    fun loadData() : List<Todo>{
        val list = mutableListOf<Todo>()
        for(i in 1 .. 100){
            val todo = Todo("todo")
            list.add(todo)
        }
        return list
    }
}