package com.android.vanguard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vanguard.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    val binding by lazy {FragmentBookmarkBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val adapter = BookmarkAdapter(loadData())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    fun loadData() : List<Bookmark>{
        val list = mutableListOf<Bookmark>()
        for(i in 1 .. 100){
            val bookmark = Bookmark("bookmark")
            list.add(bookmark)
        }
     return list
    }
}